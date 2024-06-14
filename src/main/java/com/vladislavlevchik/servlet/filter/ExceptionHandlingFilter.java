package com.vladislavlevchik.servlet.filter;

import com.vladislavlevchik.exception.SameNameException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class ExceptionHandlingFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(req, res);
        }catch (SameNameException e){
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("/new-match.jsp").forward(req, res);
        }
    }
}
