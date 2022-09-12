<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/1/2022
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/9227f672bd.js" crossorigin="anonymous"></script>
    <title>Register</title>
</head>
<body>
<center>
        <h1>Yeni hesab yaradın</h1>
</center>
        <form action="register" method="post">
            <div class="col-4 container">
                <div class="form-group">
                    <label>Email</label>
                         <input required type="email" class="form-control" placeholder="email@example.com" name="email">
                </div>
                <div class="form-group">
                    <label>Ad</label>
                    <input required type="text" class="form-control" placeholder="Adınız" name="name">
                </div>
                <div class="form-group">
                    <label>Soyadınız</label>
                    <input required type="text" class="form-control" placeholder="Soyadınız" name="surname">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input required type="password" class="form-control" placeholder="8 və daha çox simvol daxil edin" name="password">
                </div>
                <button type="submit" class="btn btn-danger" name="register">Hesab yaradın</button>
            </div>
        </form>
</body>
</html>
