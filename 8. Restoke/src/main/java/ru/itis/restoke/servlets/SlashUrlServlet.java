package ru.itis.restoke.servlets;

import com.zaxxer.hikari.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.repository.category.*;
import ru.itis.restoke.servlets.helpers.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.xml.crypto.dsig.spec.*;
import java.io.*;

@WebFilter(urlPatterns = "/*")
public class SlashUrlServlet implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HikariDataSource dataSource = ConnectionHelper.getDataSource();
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);
        dataSource.close();
        request.setAttribute("categories", categories);

        chain.doFilter(request, response);
    }
}
