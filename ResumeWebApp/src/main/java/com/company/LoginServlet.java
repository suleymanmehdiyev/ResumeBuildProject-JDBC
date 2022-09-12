package com.company;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.company.dao.inter.UserDAOInter;
import com.company.main.Context;
import com.company.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserDAOInter userDao = Context.instanceUserDAO();

    private BCrypt.Verifyer verifyer = BCrypt.verifyer();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userDao.findByUserEmail(email);

            if (user == null) {
                request.getRequestDispatcher("error.jsp?msg=user doesn`t exist!!!").forward(request,response);
            }
            BCrypt.Result rs = verifyer.verify(password.toCharArray(), user.getPassword().toCharArray());

            if (!rs.verified ) {
                throw new IllegalArgumentException("password is incorrect!!!");
            }

            if(email.equals("admin@gmail.com")){
                response.sendRedirect("users");
            }else{
                request.getSession().setAttribute("loggedInUser", user);
                response.sendRedirect("step1");
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendRedirect("error?msg=" + ex.getMessage());
        }
    }
}
