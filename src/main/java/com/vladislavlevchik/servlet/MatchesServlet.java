package com.vladislavlevchik.servlet;

import com.vladislavlevchik.repository.MatchRepository;
import com.vladislavlevchik.dto.MatchResponseDto;
import com.vladislavlevchik.entity.Match;
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

    private final MatchRepository matchRepository = new MatchRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String filterByPlayerName = req.getParameter("filter_by_player_name");
        String pageParam = req.getParameter("page");

        int page = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;

        int pageSize = 5;
        List<Match> allMatches;

        if (filterByPlayerName == null) {
            allMatches = matchRepository.findAll();
        } else {
            filterByPlayerName = filterByPlayerName.toUpperCase();
            allMatches = matchRepository.findAllMatchesByPlayerName(filterByPlayerName);
        }

        int totalMatches = allMatches.size();
        int totalPages = (int) Math.ceil((double) totalMatches / pageSize);

        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, totalMatches);

        List<MatchResponseDto> matches = allMatches.subList(start, end)
                .stream()
                .map(MapperUtil::convertToDto)
                .toList();

        req.setAttribute("matches", matches);
        req.setAttribute("currentPage", page);
        req.setAttribute("totalPages", totalPages);

        req.getRequestDispatcher("/matches.jsp").forward(req, resp);
    }
}
