package com.company;

import com.company.dao.inter.*;
import com.company.main.Context;
import com.company.model.ContactDetail;
import com.company.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserDetailController", value = "/userdetail")
public class UserDetailController extends HttpServlet {
    private UserDAOInter userDao = Context.instanceUserDAO();
    private ContactDetailDAOInter cdDao = Context.instanceContactDetailDAO();
    private EducationDAOInter eduDao = Context.instanceEducationDAO();
    private UserSkillDAOInter userSkillDao = Context.instanceUserSkillDAO();
    private EmploymentHistoryDAOInter empDao = Context.instanceEmploymentHistoryDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("userdetail.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        User user = userDao.getById(id);
        String action = request.getParameter("action");
        if(action.equals("info")) {
            request.getSession().setAttribute("user",user);
            response.sendRedirect("userdetail");
        }else if(action.equals("delete")){
            cdDao.deleteContactDetail(id);
            eduDao.deleteEducation(id);
            userSkillDao.deleteUserSkill(id);
            empDao.deleteEmploymentHistory(id);
            userDao.deleteUser(id);
            response.sendRedirect("users");
        }
    }
}
