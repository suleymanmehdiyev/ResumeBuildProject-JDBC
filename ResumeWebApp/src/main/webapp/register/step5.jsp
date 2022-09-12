<%@ page import="com.company.main.Context" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.dao.inter.UniversityDAOInter" %>
<%@ page import="com.company.dao.inter.EducationDAOInter" %>
<%@ page import="com.company.dao.inter.EmploymentHistoryDAOInter" %>
<%@ page import="com.company.model.*" %>
<%@ page import="com.company.dao.inter.UserSkillDAOInter" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/2/2022
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/9227f672bd.js" crossorigin="anonymous"></script>
    <title>Title</title>
    <style>
        .buttons{
            display: inline-flex;
            margin-top: 20px;
            margin-left: 5px;
        }
        .action_btn{
            margin-left: 10px;
        }
        .steps{
            margin-top: 5px;
            margin-left: 25px;
        }
    </style>
</head>
<body>
<%User u = (User) session.getAttribute("loggedInUser");
    UserSkillDAOInter userSkillDAO = Context.instanceUserSkillDAO();
    List<UserSkill> userSkillList = userSkillDAO.getAllUserSkill(u.getId());

%>

<div class="container">
    <div class="buttons">
        <form action="step4" method="get">
            <input type="hidden" name="userId" value="<%=u.getId()%>">
            <button class="btn btn-danger action_btn" name="previous" value="Previous">
                Previous
            </button>
        </form>
        <p class="steps">5/5 Addım</p>
        <div style="padding-left: 700px">
            <form action="logout" method="post">
                <input type="hidden" name="userId" value="<%=u.getId()%>">
                <button class="btn btn-primary action_btn" name="logout" value="Logout">
                    Logout
                </button>
            </form>
        </div>
    </div>
    <center>
        <h2>Bilik və bacarıqlar</h2>
        <hr>
    </center>
    <form action="step5" method="post">
        <div class="col-4 container">
            <input type="hidden" name="userId" value="<%=u.getId()%>">

            <div class="form-group">
                <label>Bilik və bacarıqlar</label>
                <div style="display: inline-flex;width: 100%">
                    <input type="text" class="form-control" name="skillName" placeholder="Xüsusi bacarıqlarınızı daxil edin">

                    <button class="btn btn-secondary" type="submit" name="action" value="add" >
                        <i class="fas fa-plus"></i>
                    </button>
                </div>
            </div>
            <div class="form-group">
                <%for(int i=0;i<userSkillList.size();i++){%>
                <div style="display: inline-flex;margin-bottom: 5px">
                    <input type="hidden" name="skillId" value="<%=userSkillList.get(i).getId()%>">
                    <input disabled class="form-control" type="text" value="<%=userSkillList.get(i).getSkill().getSkillName()%>"><br>
                    <button class="btn btn-secondary" id="idForDelete" onclick="setIdForDelete(<%=userSkillList.get(i).getId()%>)" type="submit" name="action" value="delete" >
                        <i class="fas fa-minus"></i>
                    </button>
                </div>
                <%}%>

            </div>
        </div>
    </form>
    <form action="final" method="get">
        <center>
            <button type="submit" class="btn btn-danger" name="home">Növbəti addım</button>
        </center>
    </form>
</div>
<script>
    function setIdForDelete(id){
        var elem = document.getElementById("idForDelete");
        elem.value = id;
    }
</script>
</body>
</html>
