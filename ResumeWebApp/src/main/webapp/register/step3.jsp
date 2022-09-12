<%@ page import="com.company.model.User" %>
<%@ page import="com.company.model.City" %>
<%@ page import="com.company.main.Context" %>
<%@ page import="com.company.dao.inter.CityDAOInter" %>
<%@ page import="java.util.List" %>
<%@ page import="com.company.dao.inter.UserDAOInter" %>
<%@ page import="com.company.model.University" %>
<%@ page import="com.company.dao.inter.UniversityDAOInter" %>
<%@ page import="com.company.dao.inter.EducationDAOInter" %>
<%@ page import="com.company.model.Education" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/2/2022
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
          integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/9227f672bd.js" crossorigin="anonymous"></script>
    <title>Title</title>
    <style>
        .buttons {
            display: inline-flex;
            margin-top: 20px;
            margin-left: 5px;
        }

        .action_btn {
            margin-left: 10px;
        }

        .steps {
            margin-top: 5px;
            margin-left: 25px;
        }
    </style>
</head>
<body>
<%
    User u = (User) session.getAttribute("loggedInUser");
    EducationDAOInter edu = Context.instanceEducationDAO();
    Education currentUserEdu = edu.getEducationById(u.getId());
    if (currentUserEdu == null) {
        Education education = new Education(null,null,null,null,null,new University(3),new User(u.getId()));
        currentUserEdu = edu.addEducation(education);
    }
    UniversityDAOInter uniDAO = Context.instanceUniversityDAO();
    List<University> uni = uniDAO.getAll();
%>

<div class="container">
    <div class="buttons">
        <form action="step2" method="get">
            <input type="hidden" name="userId" value="<%=u.getId()%>">
            <button class="btn btn-danger action_btn" name="previous" value="Previous">
                Previous
            </button>
        </form>
        <form action="step4" method="get">
            <input type="hidden" name="userId" value="<%=u.getId()%>">
            <button class="btn btn-primary action_btn" name="next" value="Next">
                Next
            </button>
        </form>
        <p class="steps">3/5 Addım</p>
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
        <h2>Təhsil</h2>
        <hr>
    </center>
    <form action="step3" method="post">
        <div class="col-4 container">
            <input type="hidden" name="userId" value="<%=u.getId()%>">
            <%if (currentUserEdu.getDegree() == null) {%>

            <div class="form-group">
                <label>Dərəcə</label><br>
                <select class="form-control" name="degree">
                    <option value="Bakalavr">Bakalavr</option>
                    <option value="Magistr">Magistr</option>
                    <option value="Ali">Ali(1997-ci ilədək)</option>
                    <option value="Orta">Orta</option>
                    <option value="Orta ixtisas">Orta ixtisas</option>
                </select>
            </div>
            <%} else {%>
            <div class="form-group">
                <label>Dərəcə</label><br>
                <select class="form-control" name="degree" value="<%=currentUserEdu.getDegree()%>">

                    <%if(currentUserEdu.getDegree().equals("Bakalavr")){%>
                        <option selected value="Bakalavr">Bakalavr</option>
                    <%}else{%>
                       <option value="Bakalavr">Bakalavr</option>
                    <%}%>

                    <%if(currentUserEdu.getDegree().equals("Magistr")){%>
                        <option selected value="Magistr">Magistr</option>
                    <%}else{%>
                        <option value="Magistr">Magistr</option>
                    <%}%>

                    <%if(currentUserEdu.getDegree().equals("Ali")){%>
                        <option selected value="Ali">Ali(1997-ci ilədək)</option>
                    <%}else{%>
                         <option value="Ali">Ali(1997-ci ilədək)</option>
                    <%}%>

                    <%if(currentUserEdu.getDegree().equals("Orta")){%>
                        <option selected value="Orta">Orta</option>
                    <%}else{%>
                        <option value="Orta">Orta</option>
                    <%}%>

                    <%if(currentUserEdu.getDegree().equals("Orta ixtisas")){%>
                         <option selected value="Orta ixtisas">Orta ixtisas</option>
                    <%}else{%>
                        <option value="Orta ixtisas">Orta ixtisas</option>
                    <%}%>
                </select>
            </div>
            <%}%>
            <%if(currentUserEdu.getUniversityId().getId()==null){%>
            <div class="form-group">
                <label>Təhsil müəssisəsi</label><br>
                <select class="form-control" name="uniId">
                    <%for (int i = 0; i < uni.size()-1; i++) {%>
                             <option value="<%=i+1%>"><%=uni.get(i)%></option>
                    <%}%>
                </select>
            </div>
            <%}else{%>
            <div class="form-group">
                <label>Təhsil müəssisəsi</label><br>
                <select class="form-control" name="uniId">
                    <%for (int i = 0; i < uni.size()-1; i++) {%>
                    <%if(currentUserEdu.getUniversityId().getId() == uni.get(i).getId()){%>
                    <option selected value="<%=i+1%>"><%=uni.get(i)%></option>
                    <%}else{%>
                    <option value="<%=i+1%>"><%=uni.get(i)%></option>
                    <%}}%>
                </select>
            </div>
            <%}%>

            <%if (currentUserEdu.getSpeciality() == null) {%>
            <div class="form-group">
                <label>İxtisas</label>
                <input required type="text" class="form-control" placeholder="İxtisası daxil edin" name="speciality">
            </div>
            <%} else {%>
            <div class="form-group">
                <label>İxtisas</label>
                <input required type="text" class="form-control" value="<%=currentUserEdu.getSpeciality()%>" name="speciality">
            </div>
            <%}%>
            <%if (currentUserEdu.getBeginDate() == null) {%>
            <div class="form-group">
                <label>Daxil olduğunuz il</label>
                <input required type="date" class="form-control" placeholder="Daxil olduğunuz ili daxil edin"
                       name="beginDate">
            </div>
            <%} else {%>
            <div class="form-group">
                <label>Daxil olduğunuz il</label>
                <input required type="date" class="form-control" value="<%=currentUserEdu.getBeginDate()%>" name="beginDate">
            </div>
            <%}%>
            <%if (currentUserEdu.getEndDate() == null) {%>
            <div class="form-group">
                <label>Bitirdiyiniz il</label>
                <input required type="date" class="form-control" placeholder="Bitirdiyiniz ili daxil edin"
                       name="endDate">
            </div>
            <%} else {%>
            <div class="form-group">
                <label>Bitirdiyiniz il</label>
                <input required type="date" class="form-control" value="<%=currentUserEdu.getEndDate()%>" name="endDate">
            </div>
            <%}%>
            <button type="submit" class="btn btn-danger" name="step3">Növbəti addım</button>
        </div>
    </form>

</div>
</body>
</html>
