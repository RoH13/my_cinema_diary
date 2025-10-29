<%--
  Created by IntelliJ IDEA.
  User: raman
  Date: 10/28/25
  Time: 12:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new movie</title>
    <style>
    body { font-family: Arial, sans-serif; margin: 40px; }
    .form-group { margin-bottom: 15px; }
    label { display: block; margin-bottom: 5px; font-weight: bold; }
    input, select { padding: 8px; width: 300px; border: 1px solid #ddd; border-radius: 4px; }
    input[type="submit"] { background: #4CAF50; color: white; border: none; padding: 10px 20px; cursor: pointer; }
    input[type="submit"]:hover { background: #45a049; }
    </style>
</head>
<body>
<form action = 'movie' method = 'post'>
    <div class="form-group">
        <label for="title">Название фильма:</label>
        <input type='text' id="title" name='title' required>
    </div>
    <div class="form-group">
        <label for="directorId">Выберите режиссера:</label>
        <select id="directorId" name='directorId' required>
            <option value="">-- Выберите режиссера --</option>
            <c:forEach var="director" items="${directors}">
                <option value="${director.id}">${director.firstName} ${director.secondName}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="genreId">Выберите жанр:</label>
        <select id="genreId" name='genreId' required>
            <option value="">-- Выберите жанр --</option>
            <c:forEach var="genre" items="${genres}">
                <option value="${genre.id}">${genre.name}</option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label for="year">Год выпуска:</label>
        <input type='number' id="year" name='year' min="1888" max="2030" required>
    </div>

    <div class="form-group">
        <label for="duration">Продолжительность (в минутах):</label>
        <input type='number' id="duration" name='duration' min="1" max="500" required>
    </div>

    <div class="form-group">
        <label for="rating">Ваша оценка (1-10):</label>
        <input type='number' id="rating" name='rating' min='1' max='10' step='1' required>
    </div>

    <input type='submit' value='Добавить фильм'>
</form>

<div style="margin-top: 20px;">
    <a href="movie?action=list">← Назад к списку фильмов</a>
</div>
</body>
</html>