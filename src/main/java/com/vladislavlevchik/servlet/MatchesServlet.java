package com.vladislavlevchik.servlet;

import com.vladislavlevchik.repository.MatchRepository;
import com.vladislavlevchik.dto.MatchResponseDto;
import com.vladislavlevchik.entity.Match;
import com.vladislavlevchik.service.FinishedMatchesPersistenceService;
import com.vladislavlevchik.utils.MapperUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {

    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filterByPlayerName = req.getParameter("filter_by_player_name");
        String pageParam = req.getParameter("page");

        int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;

        int pageSize = 5;
        List<Match> allMatches = finishedMatchesPersistenceService.find(filterByPlayerName, pageSize, page);

        long totalMatches = finishedMatchesPersistenceService.getCount(filterByPlayerName);
        int totalPages = (int) Math.ceil((double) totalMatches / pageSize);

        List<MatchResponseDto> matches = allMatches
                .stream()
                .map(MapperUtil::convertToDto)
                .toList();

        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }
}
