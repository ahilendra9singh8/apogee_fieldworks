<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<title>Employee Panel</title>

<style>
body {
    font-family: Arial;
    background: #f4f6f8;
}
.container {
    width: 900px;
    margin: auto;
    background: white;
    padding: 20px;
    margin-top: 40px;
    border-radius: 8px;
}
h2 { color: #333; }
input, button {
    padding: 8px;
    margin: 5px;
}
button {
    background: #007bff;
    color: white;
    border: none;
    cursor: pointer;
}
table {
    width: 100%;
    border-collapse: collapse;
}
table th, td {
    border: 1px solid #ccc;
    padding: 8px;
}
.error {
    color: red;
}
.logout {
    float: right;
}
</style>

</head>
<body>

<div class="container">

<!-- LOGIN VIEW -->
<c:if test="${view == 'login'}">
    <h2>Login</h2>

    <form action="${pageContext.request.contextPath}/employee/page/login" method="post">
        Username: <input type="text" name="username" /><br/>
        Password: <input type="password" name="password" /><br/>
        <button type="submit">Login</button>
    </form>

    <div class="error">${error}</div>
</c:if>

<!-- CRUD VIEW -->
<c:if test="${view == 'crud'}">

    <a class="logout" href="${pageContext.request.contextPath}/employee/page/logout">Logout</a>

    <h2>Employee Form</h2>

    <form action="${pageContext.request.contextPath}/employee/page/save" method="post">
        <input type="hidden" name="id" value="${employee.id}" />
        Name: <input type="text" name="name" value="${employee.name}" />
        Role: <input type="text" name="role" value="${employee.role}" />
        Privilege: <input type="text" name="privilege" value="${employee.privilege}" />
        <button type="submit">
            <c:choose>
                <c:when test="${employee.id != null}">Update</c:when>
                <c:otherwise>Save</c:otherwise>
            </c:choose>
        </button>
    </form>

    <hr/>

    <h2>Employee List</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Role</th>
            <th>Action</th>
        </tr>

        <c:forEach items="${list}" var="e">
            <tr>
                <td>${e.id}</td>
                <td>${e.name}</td>
                <td>${e.role}</td>
                <td>
                    <a href="edit/${e.id}">Edit</a> |
                    <a href="delete/${e.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</c:if>

</div>

</body>
</html>