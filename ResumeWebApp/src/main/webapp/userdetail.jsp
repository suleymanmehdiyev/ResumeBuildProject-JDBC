<%@ page import="com.company.model.User" %>
<%@ page import="com.company.dao.inter.UserDAOInter" %>
<%@ page import="com.company.main.Context" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.company.model.UserSkill" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.company.dao.inter.UserSkillDAOInter" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/6/2022
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/9227f672bd.js" crossorigin="anonymous"></script>
    <script src="assets/users.js"></script>
    <title>Title</title>
</head>
<body>
    <%
        User u = (User) session.getAttribute("user");
        UserSkillDAOInter userSkillDAO = Context.instanceUserSkillDAO();
        List<UserSkill> userSkillList = userSkillDAO.getAllUserSkill(u.getId());

    %>

    <center>
        <h2>Şəxsi məlumatlar</h2>
        <hr>
    </center>

        <div class="col-4 container">

            <div class="form-group">
                <label>Ad</label>
                <input disabled type="text" class="form-control" placeholder="Adınız" name="name" value="<%=u.getUname()%>">
            </div>

            <div class="form-group">
                <label>Soyad</label>
                <input disabled type="text" class="form-control" placeholder="Soyadınız" name="surname" value="<%=u.getSurname()%>">
            </div>

            <div class="form-group">
                <label>Ata adı</label>
                <input disabled type="text" class="form-control" placeholder="Ata adı" name="patronymic" value="<%=u.getPatronymic()%>" >
            </div>

            <div class="form-group">
                <label>Doğum tarixi</label>
                <input disabled type="date" class="form-control" name="birthdate" placeholder="Doğum tarixini daxil edin" value="<%=u.getBirthdate()%>">
            </div>

          <% if(u.getGender().equals("KISI")){%>
            <div class="form-group">
                <label>Cins:</label>
                <label class="radio">
                    <span>Kişi</span>
                    <input checked="checked" type="radio" name="gender" value="KISI">
                </label>
                <label>
                    <span>Qadın</span>
                    <input type="radio" name="gender" value="QADIN">
                </label>
            </div>
            <%}else if(u.getGender().equals("QADIN")){%>
            <div class="form-group">
                <label>Cins:</label>
                <label class="radio">
                    <span>Kişi</span>
                    <input type="radio" name="gender" value="KISI">
                </label>
                <label>
                    <span>Qadın</span>
                    <input checked="checked" type="radio" name="gender" value="QADIN">
                </label>
            </div>
            <%}%>

            <%if(u.getMaritalStatus().equals("SUBAY")){%>
            <div class="form-group">
                <label>Ailə vəziyyəti:</label>
                <label class="radio">
                    <span>Subay</span>
                    <input checked="checked" type="radio" name="marstatus" value="SUBAY">
                </label>
                <label>
                    <span>Evli</span>
                    <input type="radio" name="marstatus" value="EVLI">
                </label>
            </div>
            <%}else if(u.getMaritalStatus().equals("EVLI")){%>
            <div class="form-group">
                <label>Ailə vəziyyəti:</label>
                <label class="radio">
                    <span>Subay</span>
                    <input type="radio" name="marstatus" value="SUBAY">
                </label>
                <label>
                    <span>Evli</span>
                    <input checked="checked" type="radio" name="marstatus" value="EVLI">
                </label>
            </div>
            <%}%>

            <div class="form-group">
                <label>Vətandaşlıq</label>
                <input disabled type="text" class="form-control" name="citizenship" placeholder="Vətandaşlığınızı daxil edin" value="<%=u.getCitizenship()%>">
            </div>

            <% if(u.getMilitary().equals("XEYR")){%>
            <div class="form-group">
                <label>Hərbi mükəlləfiyyət:</label>
                <label class="radio">
                    <span>Xeyr</span>
                    <input checked="checked" type="radio" name="military" value="XEYR">
                </label>
                <label>
                    <span>Bəli</span>
                    <input type="radio" name="military" value="BELI">
                </label>
            </div>
            <%}else if(u.getMilitary().equals("BELI")){%>
            <div class="form-group">
                <label>Hərbi mükəlləfiyyət:</label>
                <label class="radio">
                    <span>Xeyr</span>
                    <input type="radio" name="military" value="XEYR">
                </label>
                <label>
                    <span>Bəli</span>
                    <input checked="checked" type="radio" name="military" value="BELI">
                </label>
            </div>
            <%}%>

            <%if(u.getAbout()==null){%>
            <div class="form-group">
                <label>Haqqında</label>
                <input disabled type="text" class="form-control" name="about"placeholder="Haqqınızda daxil edin:" >
            </div>
            <%}else{%>
            <div class="form-group">
                <label>Haqqında</label>
                <input disabled type="text" class="form-control" name="about" value="<%=u.getAbout()%>">
            </div>
            <%}%>
        </div>


    <center>
        <h2>Əlaqə məlumatlar</h2>
        <hr>
    </center>

        <div class="col-4 container">
            <div class="form-group">
                <label>Telefon nömrəsi</label>
                <input disabled type="" class="form-control" value="<%=u.getContactDetail().getPhoneNumber()%>" name="phone"  >
            </div>

            <div class="form-group">
                <label>Qeydiyyat ünvanı</label>
                <input disabled type="text" class="form-control" value="<%=u.getContactDetail().getRegAddress().getCityName()%>" name="cityId">
            </div>

            <div class="form-group">
                <label>Faktiki ünvan</label>
                <input disabled type="text" class="form-control" value="<%=u.getContactDetail().getActualAddress()%>" name="actualAddress"  >
            </div>
        </div>

    <center>
        <h2>Təhsil</h2>
        <hr>
    </center>

        <div class="col-4 container">
            <%for(int i=0;i<u.getEducation().size();i++){%>
                <div class="form-group">
                    <label>Dərəcə</label><br>
                    <input disabled class="form-control" type="text" value="<%=u.getEducation().get(i).getDegree()%>">
                </div>

                <div class="form-group">
                    <label>Təhsil müəssisəsi</label><br>
                    <input disabled class="form-control" type="text" value="<%=u.getEducation().get(i).getUniversityId().getUniName()%>">
                </div>

                <div class="form-group">
                    <label>İxtisas</label>
                    <input disabled type="text" class="form-control" value="<%=u.getEducation().get(i).getSpeciality()%>" name="speciality">
                </div>

                <div class="form-group">
                    <label>Daxil olduğunuz il</label>
                    <input disabled type="date" class="form-control" value="<%=u.getEducation().get(i).getBeginDate()%>" name="beginDate">
                </div>

                <div class="form-group">
                    <label>Bitirdiyiniz il</label>
                    <input disabled type="date" class="form-control" value="<%=u.getEducation().get(i).getEndDate()%>" name="endDate">
                </div>
        </div>
        <%}%>

    <center>
        <h2>İş təcrübəsi</h2>
        <hr>
    </center>

        <div class="col-4 container">
            <%for(int i=0;i<u.getEducation().size();i++){%>

            <div class="form-group">
                <label>Şirkət adı</label><br>
                <input disabled class="form-control " type="text" name="company" value="<%=u.getEmploymentHistory().get(i).getCompanyName()%>">
            </div>

            <div class="form-group">
                <label>Vəzifə</label>
                <input disabled type="text" class="form-control" name="position" value="<%=u.getEmploymentHistory().get(i).getPosition()%>">
            </div>

            <div class="form-group">
                <label>Vəzifə öhdəlikləri</label>
                <input disabled type="text" class="form-control" name="responsibilities"  value="<%=u.getEmploymentHistory().get(i).getJobDesc()%>" >
            </div>

            <div class="form-group">
                <label>İşə başlama tarixi </label>
                <input disabled type="date" class="form-control" value="<%=u.getEmploymentHistory().get(i).getBeginDate()%>"  name="beginDate"  >
            </div>
            <div class="form-group">
                <label>İşdən çıxma tarixi</label>
                <input disabled type="date" class="form-control"  name="endDate" value="<%=u.getEmploymentHistory().get(i).getEndDate()%>" >
            </div>
        </div>
    <%}%>

    <center>
        <h2>Bilik və bacarıqlar</h2>
        <hr>
    </center>
    <div class="col-4 container">
        <div class="form-group">
            <%for(UserSkill us : userSkillList){%>
            <input class="form-control" style="margin-bottom: 5px" disabled type="text" name="skills" value="<%=us.getSkill().getSkillName()%>">
            <%}%>
        </div>
    </div>


</body>
</html>
