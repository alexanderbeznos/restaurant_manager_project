<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <form:form action="./registration" method="post" modelAttribute="user">
        <div>
            <label for="lastName">Last name</label>
            <input type="text" id="lastName" name="lastName" placeholder="Enter last name">
        </div>
        <div>
            <label for="firstName">First name</label>
            <input type="text" id="firstName" name="firstName" placeholder="Enter first name">
        </div>
        <div>
            <label for="middleName">Middle name</label>
            <input type="text" id="middleName" name="middleName" placeholder="Enter middle name">
        </div>
        <div>
            <label for="login">Login</label>
            <input type="text" id="login" name="login" placeholder="Enter login">
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter password">
        </div>
        <div>
            <button type="submit">Registration</button>
        </div>

        <c:forEach items="${requestScope.result.allErrors}" var="error">
            <b style="color:red"><c:out value="${error.defaultMessage}"/></b>
            <br>
        </c:forEach>

    </form:form>
</body>
</html>



