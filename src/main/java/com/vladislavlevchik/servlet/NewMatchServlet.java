package com.vladislavlevchik.servlet;

import com.vladislavlevchik.dto.PlayerRequestDto;
import com.vladislavlevchik.service.OngoingMatchesService;
import com.vladislavlevchik.utils.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PlayerRequestDto playerOneDto = PlayerRequestDto.builder()
                .name(req.getParameter("playerOne").toUpperCase())
                .build();

        PlayerRequestDto playerTwoDto = PlayerRequestDto.builder()
                .name(req.getParameter("playerTwo").toUpperCase())
                .build();

        ValidationUtil.validate(playerOneDto, playerTwoDto);

        UUID uuid = ongoingMatchesService.createMatch(playerOneDto, playerTwoDto);

        resp.sendRedirect("/match-score?uuid=" + uuid);
    }
}
