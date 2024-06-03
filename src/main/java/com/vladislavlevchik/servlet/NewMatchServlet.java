package com.vladislavlevchik.servlet;

import com.vladislavlevchik.dao.PlayerDao;
import com.vladislavlevchik.dto.MatchScoreDto;
import com.vladislavlevchik.entity.Player;
import com.vladislavlevchik.service.OngoingMatchesService;
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
    private final PlayerDao playerDao = new PlayerDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    //TODO надо не забыть сделать так, чтобы
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String playerOneName = req.getParameter("playerOne");
        String playerTwoName = req.getParameter("playerTwo");

        if (playerOneName.equals(playerTwoName)) {
            req.setAttribute("error", "Players must be different.");
            req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
            return;
        }

        Player playerOne = playerDao.findByName(playerOneName)
                .orElseGet(() -> playerDao.save(
                        Player.builder()
                                .name(playerOneName)
                                .build()));

        Player playerTwo = playerDao.findByName(playerTwoName)
                .orElseGet(() -> playerDao.save(
                        Player.builder()
                                .name(playerTwoName)
                                .build()));

        MatchScoreDto matchScoreDto = MatchScoreDto.builder()
                .playerOne(convertToDto(playerOne))
                .playerTwo(convertToDto(playerTwo))
                .build();

        UUID uuid = UUID.randomUUID();

        ongoingMatchesService.addMatch(uuid, matchScoreDto);

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
