package com.company.register;

import com.company.config.Config;
import com.company.dao.inter.UserDAOInter;
import com.company.main.Context;
import com.company.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Step1", value = "/step1")
public class Step1 extends HttpServlet {
    private UserDAOInter userDAO = Context.instanceUserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register/step1.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String uname = request.getParameter("name");
            String surname = request.getParameter("surname");
            String patronymic = request.getParameter("patronymic");
            String gender = request.getParameter("gender");
            String marstatus = request.getParameter("marstatus");
            String citizenship = request.getParameter("citizenship");
            String military = request.getParameter("military");
            String about = request.getParameter("about");
            String birthdate = request.getParameter("birthdate");

            User u = new User(id,uname,surname,patronymic, Config.parseDate(birthdate), gender,marstatus,citizenship,military,about);
            userDAO.updateUser(u);
            response.sendRedirect("step2");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
