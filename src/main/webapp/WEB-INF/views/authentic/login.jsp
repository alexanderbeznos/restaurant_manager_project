<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
    <body>
        <form action="./login/process" method="post">
            <div>
                <label for="login">Login:</label>
                <input type="text" id="login" name="login" placeholder="Enter login">
            </div>
            <div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter password">
            </div>
            <div>
                <button type="submit">Log in</button>
            </div>
        </form>
        <c:if test="${requestScope.errorKey}">
            <P>Bad credentials</P>
        </c:if>
    </body>
</html>




