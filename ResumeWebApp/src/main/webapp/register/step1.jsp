<%@ page import="com.company.model.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/2/2022
  Time: 6:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="assets/step1.css">
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
%>

<div class="container">
    <div class="buttons">
        <form action="step1" method="get">
            <input type="hidden" name="userId" value="<%=u.getId()%>">
            <button class="btn btn-danger action_btn" name="previous" value="Previous">
                Previous
            </button>
        </form>
        <form action="step2" method="get">
            <input type="hidden" name="userId" value="<%=u.getId()%>">
            <button class="btn btn-primary action_btn" name="next" value="Next">
                Next
            </button>
        </form>
        <p class="steps">1/5 Addım</p>
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
        <h2>Şəxsi məlumatlar</h2>
        <hr>
    </center>

    <form action="step1" method="post">
        <div class="col-4 container">
            <input type="hidden" name="id" value="<%=u.getId()%>">
            <div class="form-group">
                <label>Ad</label>
                <input required type="text" class="form-control" placeholder="Adınız" name="name" value="<%=u.getUname()%>">
            </div>
            <div class="form-group">
                <label>Soyad</label>
                <input required type="text" class="form-control"  name="surname" value="<%=u.getSurname()%>">
            </div>

            <%if(u.getPatronymic() == null){%>
            <div class="form-group">
                <label>Ata adı</label>
                <input required type="text" class="form-control" placeholder="Ata adı" name="patronymic" >
            </div>
            <%}else{%>
            <div class="form-group">
                <label>Ata adı</label>
                <input required type="text" class="form-control"  name="patronymic" value="<%=u.getPatronymic()%>">
            </div>
            <%}%>
            <%if(u.getBirthdate() == null){%>
            <div class="form-group">
                <label>Doğum tarixi</label>
                <input required type="date" class="form-control" name="birthdate" placeholder="Doğum tarixini daxil edin" >
            </div>
            <%}else{%>
            <div class="form-group">
                <label>Doğum tarixi</label>
                <input required type="date" class="form-control" name="birthdate" value="<%=u.getBirthdate()%>">
            </div>
            <%}%>


            <%if(u.getGender()==null){%>
            <div class="form-group">
                <label>Cins:</label>
                <label class="radio">
                    <span>Kişi</span>
                    <input type="radio" name="gender" value="KISI">
                </label>
                <label>
                    <span>Qadın</span>
                    <input type="radio" name="gender" value="QADIN">
                </label>
            </div>
            <%}else if(u.getGender().equals("KISI")){%>
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

            <%if(u.getMaritalStatus()==null){%>
                 <div class="form-group">
                    <label>Ailə vəziyyəti:</label>
                    <label class="radio">
                        <span>Subay</span>
                        <input type="radio" name="marstatus" value="SUBAY">
                    </label>
                    <label>
                        <span>Evli</span>
                        <input type="radio" name="marstatus" value="EVLI">
                    </label>
                </div>
            <%}else if(u.getMaritalStatus().equals("SUBAY")){%>
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

            <%if(u.getCitizenship()!=null){%>
            <div class="form-group">
                <label>Vətandaşlıq</label>
                <input required type="text" class="form-control" name="citizenship" placeholder="Vətandaşlığınızı daxil edin" value="<%=u.getCitizenship()%>">
            </div>
            <%}else{%>
            <div class="form-group">
                <label>Vətandaşlıq</label>
                <input required type="text" class="form-control" name="citizenship" placeholder="Vətandaşlığınızı daxil edin">
            </div>
            <%}%>
            <%if(u.getMilitary()==null){%>
                <div class="form-group">
                    <label>Hərbi mükəlləfiyyət:</label>
                    <label class="radio">
                        <span>Xeyr</span>
                        <input type="radio" name="military" value="XEYR">
                    </label>
                    <label>
                        <span>Bəli</span>
                        <input type="radio" name="military" value="BELI">
                    </label>
                </div>
            <%}else if(u.getMilitary().equals("XEYR")){%>
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

            <%if(u.getAbout()!=null){%>
                <div class="form-group">
                    <label>Haqqında</label>
                    <input required type="text" class="form-control" name="about" value="<%=u.getAbout()%>">
                </div>
            <%}else{%>
                <div class="form-group">
                    <label>Haqqında</label>
                    <input required type="text" class="form-control" name="about" placeholder="Haqqınızda daxil edin:">
                </div>
            <%}%>
            <button type="submit" class="btn btn-danger" name="step1">Növbəti addım</button>
        </div>
    </form>

</div>
</body>
</html>
