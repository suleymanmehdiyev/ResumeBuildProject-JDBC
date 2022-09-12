package com.company.register;

import com.company.config.Config;
import com.company.dao.inter.EmploymentHistoryDAOInter;
import com.company.dao.inter.SkillDAOInter;
import com.company.dao.inter.UserDAOInter;
import com.company.dao.inter.UserSkillDAOInter;
import com.company.main.Context;
import com.company.model.EmploymentHistory;
import com.company.model.Skill;
import com.company.model.User;
import com.company.model.UserSkill;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Step5", value = "/step5")
public class Step5 extends HttpServlet {
    SkillDAOInter skillDAOInter = Context.instanceSkillDAO();
    UserSkillDAOInter userSkillDAOInter = Context.instanceUserSkillDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("register/step5.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            Integer skillId= null;
            if(request.getParameter("skillId")!=null ) {
                 skillId = Integer.parseInt(request.getParameter("skillId"));
            }
            String action = request.getParameter("action");
            String skillName = request.getParameter("skillName");
            Skill checkSkill = skillDAOInter.getByName(skillName);
            UserSkill us = null;
            if(action.equals("add") && checkSkill==null){
               Skill newSkill = skillDAOInter.addSkill(new Skill(null,skillName));
               Skill getSkillId =  skillDAOInter.getByName(newSkill.getSkillName());
               us = new UserSkill(null,new User(userId),new Skill(getSkillId.getId()));
               userSkillDAOInter.addUserSkill(us);
            }else if(checkSkill != null){
               us = new UserSkill(null,new User(userId),new Skill(checkSkill.getId()));
               userSkillDAOInter.addUserSkill(us);
            }else if(action.equals("delete")){
                userSkillDAOInter.deleteUserSkill(skillId);
            }
            request.getRequestDispatcher("register/step5.jsp").forward(request,response);
        }catch (Exception ex){
            ex.printStackTrace();
            response.sendRedirect("error?msg= " + ex.getMessage());
        }
    }
}
