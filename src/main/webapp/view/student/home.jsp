
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Quản lý học sinh</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
</head>
<body>
<div style="text-align: center">
    <h1>Manager Student</h1>
    <form action="/student?action=search" method="post">
        <input style="width: 300px;" type="text" class="search" name="search" id="search"
               placeholder="Search Name Student ...">
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
    </form>
</div>



<h4 style="margin-left: 650px">
    <a href="/student?action=create">Add New Student</a>
</h4>
<div style="text-align: center">
    <form action="" method="get">
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Birth Day</th>
                <th>Address</th>
                <th>Phone Number</th>
                <th>Class Room</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${students}" var="student">
                <tr>
                    <td>${student.studentId}</td>
                    <td>${student.name}</td>
                    <td>${student.email}</td>
                    <td>${student.dateOfBirth}</td>
                    <td>${student.address}</td>
                    <td>${student.phoneNumber}</td>
                    <td>${student.classRoom.className}</td>
                    <td>
                        <div style="Display:flex; flex-direction: row">
                            <form action="<c:url value="/student"/>" method="get">
                                <input type="hidden" name="action" value="edit">
                                <input type="hidden" name="studentId" value="${student.studentId}">
                                <button type="submit" style="text-align: center">Edit</button>
                            </form>
                            <div>
                                <button style="text-align: center" type="button" data-toggle="modal"
                                        data-target="#myModal${student.studentId}">Delete
                                </button>
                                <div class="modal fade" id="myModal${student.studentId}" role="dialog ">
                                    <div class="modal-dialog modal-lg" style="width: 300px; text-align: center">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">
                                                </button>
                                                <h4 class="modal-title">Bạn chắn chắn xóa</h4>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                    Close
                                                </button>
                                                <form action="<c:url value="/student"/>" method="get">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="studentId" value="${student.studentId}">
                                                    <button type="submit" class="btn btn-default">Ok</button>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
</div>
</body>
</html>
