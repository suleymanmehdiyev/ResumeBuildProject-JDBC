package com.company.register;

import com.company.config.Config;
import com.company.dao.inter.ContactDetailDAOInter;
import com.company.dao.inter.EducationDAOInter;
import com.company.dao.inter.UniversityDAOInter;
import com.company.main.Context;
import com.company.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet(name = "Step3", value = "/step3")
public class Step3 extends HttpServlet {
    EducationDAOInter educationDAO = Context.instanceEducationDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("register/step3.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String degree = request.getParameter("degree");
            String speciality = request.getParameter("speciality");
            String beginDate = request.getParameter("beginDate");
            String endDate = request.getParameter("endDate");
            int uniId = Integer.parseInt(request.getParameter("uniId"));


           //find education to userid
            Education edu = educationDAO.getEducationById(userId);

            Education newEdu = null;


            if(edu==null){
                newEdu =new Education(null, degree, speciality, Config.parseDate(beginDate),Config.parseDate(endDate),new University(uniId), new User(userId));
                educationDAO.addEducation(newEdu);
            }else{
                newEdu = new Education(edu.getId(), degree, speciality, Config.parseDate(beginDate),Config.parseDate(endDate),new University(uniId), new User(userId));
                educationDAO.updateEducation(newEdu);
            }
            response.sendRedirect("step4");
        }catch (Exception ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg= " + ex.getMessage());
        }
    }
}
