package com.vladislavlevchik.servlet.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@WebListener
public class ConfigListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Properties prop = new Properties();
        int pageSize = 5; // Значение по умолчанию

        try (InputStream input = sce.getServletContext().getResourceAsStream("/WEB-INF/classes/config.properties")) {
            if (input != null) {
                prop.load(input);
                String pageSizeStr = prop.getProperty("page.size", "5"); // Получение значения свойства или значения по умолчанию
                pageSize = Integer.parseInt(pageSizeStr); // Преобразование строки в целое число
            }

            sce.getServletContext().setAttribute("pageSize", Integer.parseInt(prop.getProperty("page.size")));
        } catch (IOException | NumberFormatException ex) {
            // Используем по умолчанию
        }

        sce.getServletContext().setAttribute("pageSize", pageSize);
    }
}
