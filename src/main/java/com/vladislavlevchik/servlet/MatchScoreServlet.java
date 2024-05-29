package com.vladislavlevchik.servlet;

import com.vladislavlevchik.dto.PlayerScoreResponseDto;
import com.vladislavlevchik.entity.MatchScore;
import com.vladislavlevchik.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private final OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");

        MatchScore match = ongoingMatchesService.findById(uuid);

        req.setAttribute("match", match);

        req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Sosi pisu");
    }
}
