<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>meals</title>
</head>
<body>
<table>

        <c:forEach items="${meals}" var="itemBean">
             <c:set var="bool" value="${itemBean.exceed}"/>
                <c:choose>
                    <c:when test="${bool==true}">
                        <tr style="color: red">
                        <td>${itemBean.description}</td> <td></td> <td>${itemBean.calories}</td> <td></td> <td> ${itemBean.dateTime}</td>Pfhf

                    </c:when>
                    <c:when test="${bool==false}">
                        <tr style="color: green">
                        <td>${itemBean.description}</td> <td></td> <td>${itemBean.calories}</td> <td></td> <td> ${itemBean.dateTime}</td>
                    </c:when>
                </c:choose>
                <td>${itemBean.description}</td> <td></td> <td>${itemBean.calories}</td> <td></td> <td> ${itemBean.dateTime}</td>

            </tr>

        </c:forEach>

</table>
</body>
</html>
