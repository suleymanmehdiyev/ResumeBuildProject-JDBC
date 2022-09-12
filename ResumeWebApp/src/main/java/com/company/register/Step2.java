package com.company.register;

import com.company.dao.inter.ContactDetailDAOInter;
import com.company.main.Context;
import com.company.model.City;
import com.company.model.ContactDetail;
import com.company.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Step2", value = "/step2")
public class Step2 extends HttpServlet {
    ContactDetailDAOInter detail = Context.instanceContactDetailDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("register/step2.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String phone = request.getParameter("phone");
            String actualAddress = request.getParameter("actualAddress");
            int cityId = Integer.parseInt(request.getParameter("cityId"));
           //find condactdetail to userid
            ContactDetail cd = detail.getContactDetailById(userId);

            ContactDetail newDetail = null;


            if(cd==null){
                newDetail =new ContactDetail(null, phone, actualAddress, new City(cityId), new User(userId));
                detail.addContactDetail(newDetail);
            }else{
                newDetail = new ContactDetail(cd.getId(), phone, actualAddress, new City(cityId), new User(userId));
                detail.updateContactDetail(newDetail);
            }
            response.sendRedirect("step3");
        }catch (Exception ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg= " + ex.getMessage());
        }
    }
}
