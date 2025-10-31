<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Films</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h2>Список режиссеров</h2>

<table>
    <thead>
    <tr>

        <th>First name</th>
        <th>Second name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="director" items="${directors}">
        <tr>
           <td> <a href = "director?action=movies&id=${director.getId()}">
                ${director.getFirstName()} </a></td>

            <td> <a href = "director?action=movies&id=${director.getId()}">
                    ${director.getSecondName()} </a> </td>


        </tr>
    </c:forEach>

    <%-- Если список пуст --%>
    <c:if test="${empty directors}">
        <tr>
            <td colspan="2" style="text-align: center;">Нет данных</td>
        </tr>
    </c:if>
    </tbody>
</table>
<div style="margin-top: 20px;">
    <a href="index.jsp">← На главную</a>
</div>
</body>
</html>
