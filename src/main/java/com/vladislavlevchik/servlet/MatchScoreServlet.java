package com.vladislavlevchik.servlet;

import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.dto.PlayerScoreDto;
import com.vladislavlevchik.entity.Match;
import com.vladislavlevchik.entity.Player;
import com.vladislavlevchik.service.FinishedMatchesPersistenceService;
import com.vladislavlevchik.service.MatchScoreCalculationService;
import com.vladislavlevchik.service.OngoingMatchesService;
import com.vladislavlevchik.utils.MapperUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static com.vladislavlevchik.utils.MapperUtil.*;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();
    private final MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();
    private final FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));

        MatchScoreDto match = ongoingMatchesService.findById(uuid);

        req.setAttribute("match", match);
        req.setAttribute("uuid", uuid);

        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Long id = Long.parseLong(req.getParameter("playerId"));

        MatchScoreDto match = ongoingMatchesService.findById(uuid);

        matchScoreCalculationService.updateScore(match, id);

        PlayerScoreDto playerOne = match.getPlayerOne();
        PlayerScoreDto playerTwo = match.getPlayerTwo();

        if (playerOne.getSets() == 2 || playerTwo.getSets() == 2) {
            finishedMatchesPersistenceService.persist(match);
            ongoingMatchesService.removeFromMatches(uuid);

            req.setAttribute("match", convertToMatchResponseDto(match));

            req.getRequestDispatcher("/finish-match.jsp").forward(req, resp);
        }

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
