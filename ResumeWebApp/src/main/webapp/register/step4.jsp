<%@ page import="com.company.main.Context" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.dao.inter.UniversityDAOInter" %>
<%@ page import="com.company.dao.inter.EducationDAOInter" %>
<%@ page import="com.company.dao.inter.EmploymentHistoryDAOInter" %>
<%@ page import="com.company.model.*" %><%--
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
    EmploymentHistoryDAOInter emp = Context.instanceEmploymentHistoryDAO();
    EmploymentHistory empUser = emp.getEmploymentHistoryById(u.getId());
    if(empUser == null){
        EmploymentHistory newEmpHistory = new EmploymentHistory(null,null,null,null,null,null,new User(u.getId()));
        empUser =  emp.addEmploymentHistory(newEmpHistory);
    }
%>

<div class="container">
    <div class="buttons">
        <form action="step3" method="get">
            <input type="hidden" name="userId" value="<%=u.getId()%>">
            <button class="btn btn-danger action_btn" name="previous" value="Previous">
                Previous
            </button>
        </form>
        <form action="step5" method="get">
            <input type="hidden" name="userId" value="<%=u.getId()%>">
            <button class="btn btn-primary action_btn" name="next" value="Next">
                Next
            </button>
        </form>
        <p class="steps">4/5 Addım</p>
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
        <h2>İş təcrübəsi</h2>
        <hr>
    </center>

    <form action="step4" method="post">
        <div class="col-4 container">
            <input type="hidden" name="userId" value="<%=u.getId()%>">

            <%if(empUser.getCompanyName()==null){%>
              <div class="form-group">
                  <label>Şirkət adı</label>
                  <input required type="text" class="form-control" name="company" placeholder="Şirkət adını daxil edin">
              </div>
            <%}else{%>
            <div class="form-group">
                <label>Şirkət adı</label><br>
                <input  class="form-control " type="text" name="company" value="<%=empUser.getCompanyName()%>">
            </div>
            <%}%>

            <%if(empUser.getPosition()==null){%>

                <div>
                    <label>Vəzifə</label>
                    <input required type="text" class="form-control" placeholder="Vəzifəni daxil edin" name="position"  name="position"  >
                </div>
            <%}else{%>
                <div class="form-group">
                    <label>Vəzifə</label>
                    <input  type="text" class="form-control" name="position" value="<%=empUser.getPosition()%>">
                </div>
            <%}%>

            <%if(empUser.getJobDesc()==null){%>
            <div class="form-group">
                <label>Vəzifə öhdəlikləri</label>
                <input required type="text" class="form-control" placeholder="Vəzifə öhdəliklərini daxil edin" name="responsibilities"  >
            </div>
            <%}else{%>
            <div class="form-group">
                <label>Vəzifə öhdəlikləri</label>
                <input type="text" class="form-control" name="responsibilities"  value="<%=empUser.getJobDesc()%>" >
            </div>
            <%}%>

            <%if(empUser.getBeginDate()==null){%>
                <div class="form-group">
                    <label>İşə başlama tarixi </label>
                    <input required type="date" class="form-control" placeholder="İşə başlama tarixini daxil edin" name="beginDate"  >
                </div>
            <%}else{%>
                <div class="form-group">
                    <label>İşə başlama tarixi </label>
                    <input  type="date" class="form-control" value="<%=empUser.getBeginDate()%>"  name="beginDate"  >
                </div>
            <%}%>
            <%if(empUser.getEndDate()==null){%>
            <div class="form-group">
                <label>İşdən çıxma tarixi </label>
                <input required type="date" class="form-control" placeholder="İşdən çıxma tarixini daxil edin"  name="endDate"  >
            </div>
            <%}else{%>
            <div class="form-group">
                <label>İşdən çıxma tarixi</label>
                <input  type="date" class="form-control"  name="endDate" value="<%=empUser.getEndDate()%>" >
            </div>
            <%}%>
            <button type="submit" class="btn btn-danger" name="step4">Növbəti addım</button>
        </div>
    </form>

</div>
</body>
</html>
