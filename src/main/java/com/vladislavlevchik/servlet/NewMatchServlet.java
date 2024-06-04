package com.vladislavlevchik.servlet;

import com.vladislavlevchik.dto.PlayerRequestDto;
import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.entity.Player;
import com.vladislavlevchik.service.OngoingMatchesService;
import com.vladislavlevchik.service.PlayerPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static com.vladislavlevchik.utils.MapperUtil.convertToDto;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    private final PlayerPersistenceService playerPersistenceService = new PlayerPersistenceService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerRequestDto playerOneDto = PlayerRequestDto.builder()
                .name(req.getParameter("playerOne").toUpperCase())
                .build();

        PlayerRequestDto playerTwoDto = PlayerRequestDto.builder()
                .name(req.getParameter("playerTwo").toUpperCase())
                .build();

        if (playerOneDto.getName().equals(playerTwoDto.getName())) {
            req.setAttribute("error", "Players must be different.");
            req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
            return;
        }

        Player playerOne = playerPersistenceService.findOrSave(playerOneDto);

        Player playerTwo = playerPersistenceService.findOrSave(playerTwoDto);

        MatchScoreDto matchScoreDto = MatchScoreDto.builder()
                .playerOne(convertToDto(playerOne))
                .playerTwo(convertToDto(playerTwo))
                .build();

        UUID uuid = UUID.randomUUID();

        ongoingMatchesService.addMatch(uuid, matchScoreDto);

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
