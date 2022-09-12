package com.company;

import com.company.dao.inter.UserDAOInter;
import com.company.main.Context;
import com.company.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private UserDAOInter userDAO = Context.instanceUserDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String password = request.getParameter("password");

            User user = userDAO.findByUserEmail(email);

            if(user!=null){
                response.sendRedirect("error?msg=Bele bir istifadeci artiq movcuddur");
            }
            if(password.length()<8){
                response.sendRedirect("error?msg=Password 8 simvoldan az ola bilmez");
            }
            else {
                User u = new User(null, name, surname, email, password);
                userDAO.addUser(u);
                response.sendRedirect("home");
            }
    }
}
