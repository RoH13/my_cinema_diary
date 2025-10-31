<%--
  Created by IntelliJ IDEA.
  User: raman
  Date: 10/31/25
  Time: 10:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit rating of the film</title>
</head>
<body>
    <form action="movie" method="put">

        <div class="form-group">
            <label for="rating">Ваша оценка (1-10):</label>
            <input type='number' id="rating" name='rating' min='1' max='10' step='1' required>
        </div>


        <input type='submit' value='Изменить рейтинг фильма ${}'>

    </form>
</body>
</html>
