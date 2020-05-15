<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="https://bootswatch.com/4/simplex/bootstrap.css" media="screen">
<link rel="stylesheet" href="https://bootswatch.com/_assets/css/custom.min.css">
<html>
<head>
    <title>Description</title>
</head>
<body>
<div class="container">
    <div class="row">F
        <div class="col-sm-8 offset-sm-2">
            <br/>
            <table class="table table-hover" id="table">
                <tr class="table-active">
                    <th>Description</th>
                    <th>ReserveTable</th>
                    <th></th>
                    <th></th>
                </tr>
                    <c:forEach items="${requestScope.orderFood}" var="orderFood">
                        <tr class="table-secondary">
                            <td><c:out value="${orderFood.description}"/></td>
                            <td><c:out value="${orderFood.reserveTables}"/></td>
                        </tr>
                    </c:forEach>


            </table>
        </div>
    </div>
</div>
<a href="./logout">Logout</a>
</body>
</html>



