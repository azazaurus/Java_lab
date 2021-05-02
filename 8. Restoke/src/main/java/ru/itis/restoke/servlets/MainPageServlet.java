package ru.itis.restoke.servlets;

import com.zaxxer.hikari.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.repository.category.*;
import ru.itis.restoke.servlets.helpers.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(urlPatterns = "/main")
public class MainPageServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HikariDataSource dataSource = ConnectionHelper.getDataSource();
//        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
//        CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);
//        dataSource.close();
//        req.setAttribute("categories", categories);

        req.getRequestDispatcher("Main_Page/main_page.ftl").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
