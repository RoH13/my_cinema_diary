<%--
  Created by IntelliJ IDEA.
  User: raman
  Date: 10/27/25
  Time: 6:50 PM
  To change this template use File | Settings | File Templates.
--%>
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
<h2>Список фильмов</h2>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>director</th>
        <th>genre</th>
        <th>year</th>
        <th>duration</th>
        <th>rating</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="movie" items="${movies}">
        <tr>
            <td>${movie.getId()}</td>
            <td>${movie.getTitle()}</td>
            <td>${movie.director.getFirstName()} ${movie.director.getSecondName()} </td>
            <td>${movie.genre.getName()}</td>
            <td>${movie.getYear()}</td>
            <td>${movie.getDuration()}</td>
            <td>${movie.getRating()}</td>
        </tr>

    </c:forEach>

    <%-- Если список пуст --%>
    <c:if test="${empty movies}">
        <tr>
            <td colspan="7" style="text-align: center;">Нет данных</td>
        </tr>
    </c:if>
    </tbody>
</table>
<div style="margin-top: 20px;">
    <a href="index.jsp">← На главную</a>
</div>
</body>
</html>
