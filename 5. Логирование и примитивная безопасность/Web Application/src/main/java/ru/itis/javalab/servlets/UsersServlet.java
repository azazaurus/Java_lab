package ru.itis.javalab.servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.NOPLogger;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.filters.LoggingFilter;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UsersRepository;
import ru.itis.javalab.repositories.UsersRepositoryJdbcImpl;
import ru.itis.javalab.services.UsersService;
import ru.itis.javalab.services.UsersServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 05.10.2020
 * 08. Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UsersService usersService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        this.usersService = (UsersService) servletContext.getAttribute("usersService");
        this.passwordEncoder = (PasswordEncoder) servletContext.getAttribute("passwordEncoder");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false) != null) {
            System.out.println(request.getSession(false).getAttribute("Hello"));
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("Hello", "Hello from server!");
        }
        // false - если сессии не было, то вернет null
        // если true, то он создаст сессию и вернет ее
        // если ничего не указано, то либо вернет существующую, либо создаст новую
        //        PrintWriter writer = response.getWriter();
//        writer.println("<h1>Users page!</h1>");
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .id(1L)
                .firstName("Марсель")
                .lastName("Сидиков")
                .age(26)
                .build());
        users.add(User.builder()
                        .id(2L)
                        .firstName("Расим")
                        .lastName("Гарипов")
                        .age(19)
                        .build());
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("/jsp/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String hashPassword = passwordEncoder.encode(password);
        System.out.println(hashPassword);
        System.out.println(passwordEncoder.matches("qwerty007", hashPassword));
        String color = request.getParameter("color");
        Cookie cookie = new Cookie("color", color);
        cookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(cookie);
        response.sendRedirect("/users");
    }
}
