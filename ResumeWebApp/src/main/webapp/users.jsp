<%@ page import="com.company.dao.inter.UserDAOInter" %>
<%@ page import="com.company.main.Context" %>
<%@ page import="com.company.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 9/6/2022
  Time: 10:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/9227f672bd.js" crossorigin="anonymous"></script>
    <script src="assets/users.js"></script>
    <title>Users</title>
</head>
<body>
<%
    UserDAOInter userDao = Context.instanceUserDAO();

    String name = request.getParameter("name");
    String surname = request.getParameter("surname");

    String patronymic = request.getParameter("patronymic");

    List<User> list = userDao.getAllNameAndSurname(name,surname,patronymic);
    // bu hissede eger surname= hecne gondermesek onda String s="" kimi qebul edecek.
%>
<div class="container">
    <div style="width: 25%">
        <div style="padding-left:20px ">
            <form action="users" method="get">
                <div class="form-group">
                    <label>name:</label>
                    <input class="form-control" type="text" name="name" value="" placeholder="Enter name"/>
                </div>
                <div class="form-group">
                    <label>surname:</label>
                    <input placeholder="Enter surname" class="form-control" type="text" name="surname" value="" />
                </div>
                <div class="form-group">
                    <label>patronymic:</label>
                    <input placeholder="Enter patronymic" class="form-control" type="text" name="patronymic" value="" />
                </div>
                <input class="btn btn-primary  " type="submit" name = "search" value="Search"/>
            </form>
        </div>
    </div>
    <div>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Surname</th>
                <th>Patronymic</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <%
                for(User u: list){
            %>
            <tr>
                <td><%=u.getUname()%></td>
                <td><%=u.getSurname()%></td>
                <td><%=u.getPatronymic()==null?"N/A":u.getPatronymic()%></td>
                <td style="width: 5px">
                    <input type="hidden" name="id" value="<%=u.getId()%>">
                    <input type="hidden" name="action" value="delete">
                    <button class="btn btn-danger" type="submit" value="delete"
                            data-toggle="modal" data-target="#exampleModal"
                            onclick="setIdForDelete(<%=u.getId()%>)">
                        <i class="fas fa-trash-alt"></i>
                    </button>
                </td>
                <td style="width: 5px">
                    <form action="userdetail" method="post">
                        <input type="hidden" name="id" value="<%=u.getId()%>">
                        <button class="btn btn-warning" type="submit" name="action" value="info" >
                            <i class="fas fa-pen-square"/></i>
                        </button>
                    </form>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure?
            </div>
            <div class="modal-footer">
                <form action="userdetail" method="post">
                    <input type="hidden" name="id" value="" id ="idForDelete">
                    <input type="hidden" name="action" value="delete">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-danger" value="Delete">
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
<script>
    function setIdForDelete(id){
        var elem = document.getElementById("idForDelete");
        elem.value = id;
    }
</script>
</body>
</html>
