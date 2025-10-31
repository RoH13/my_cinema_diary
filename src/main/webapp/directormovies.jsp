<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Фильмы режиссера</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .director-info { background: #f5f5f5; padding: 20px; border-radius: 8px; margin-bottom: 20px; }
        .movie-card { border: 1px solid #ddd; padding: 15px; margin: 10px 0; border-radius: 5px; }
        .stats { background: #e8f4fd; padding: 15px; border-radius: 5px; margin: 20px 0; }
        table { width: 100%; border-collapse: collapse; margin: 20px 0; }
        th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        th { background-color: #f2f2f2; }
        .rating { color: #ff6b00; font-weight: bold; }
    </style>
</head>
<body>
<a href="director?action=list">← Все режиссеры</a>
<a href="movie?action=list" style="margin-left: 15px;">← Все фильмы</a>

<div class="director-info">
    <h1>🎬 ${director.firstName} ${director.secondName}</h1>

    <div class="stats">
        <strong>Всего фильмов:</strong> ${movies.size()} |
        <strong>Средний рейтинг:</strong>
        <c:set var="totalRating" value="0" />
        <c:forEach var="movie" items="${movies}">
            <c:set var="totalRating" value="${totalRating + movie.rating}" />
        </c:forEach>
        <c:if test="${not empty movies}">
                <span class="rating">
                        ${totalRating / movies.size()}
                </span>/10
        </c:if>
        <c:if test="${empty movies}">
            <span>-</span>
        </c:if>
    </div>
</div>

<c:if test="${not empty movies}">
    <h2>Фильмография:</h2>
    <table>
        <thead>
        <tr>
            <th>Год</th>
            <th>Название</th>
            <th>Жанр</th>
            <th>Длительность</th>
            <th>Рейтинг</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="movie" items="${movies}">
            <tr>
                <td>${movie.year}</td>
                <td>
                    <strong>${movie.title}</strong>
                </td>
                <td>${movie.genre.name}</td>
                <td>${movie.duration} мин.</td>
                <td class="rating">${movie.rating}/10</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty movies}">
    <div style="text-align: center; padding: 40px; color: #666;">
        <h3>🎥 Фильмов не найдено</h3>
        <p>У этого режиссера пока нет фильмов в базе данных.</p>
        <a href="movie?action=showAddForm">Добавить первый фильм</a>
    </div>
</c:if>
</body>
</html>