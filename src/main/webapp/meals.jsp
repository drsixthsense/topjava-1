<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>meals</title>
</head>
<body>
<form action="/meals" method="post">
    <input type="text" name= "addDescription" value="">
    <input type="number" name = "addCalories" value="">
    <input type="datetime-local" name="addDate" value="">
    <input type="submit" name="button" value="Add">

<table>
        <c:forEach items="${meals}" var="itemBean">
             <c:set var="bool" value="${itemBean.exceed}"/>
            <thead>
            <tr>
                <th>ID</th>
                <th>Description</th>
                <th>Calories</th>
                <th>DateTime</th>
                <th></th>
            </tr>
            </thead>
                <c:choose>
                    <c:when test="${bool==true}">
                        <tr style="color: red">
                    </c:when>
                    <c:when test="${bool==false}">
                        <tr style="color: green">
                    </c:when>
                </c:choose>
                <td><c:out value="${itemBean.id}"/></td>
                <td><c:out value="${itemBean.description}" /></td>
                <td><c:out value="${itemBean.calories}" /></td>
                <td><c:out value="${itemBean.dateTime}" /></td>
                <td> <a href="/meals?action=delete&mealid=${itemBean.id}">Delete</a> </td>
            </tr>
        </c:forEach>
</table>
</form>
</body>
</html>
