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
</head>
<body>
<form action = 'addmovie' method = 'post'>
Write the film title <br>
    <input type = 'text' name = 'title'> <br>
Write director of the film <br>
    <input type = 'text' name = 'director'> <br>
Write genre of the film <br>
    <input type = 'text' name = 'genre'> <br>
Write the year of film <br>
    <input type = 'number' name = 'year' min = '1800' > <br>
Write the duration of film in minutes <br>
    <input type = 'number' name = 'duration' min = '0'> <br>
Write your rating of the film <br>
    <input type = 'number' name = 'rating' min = '1' max = '10' step = '1' required> <br>
<input type = 'submit' name = 'submit'>
</form>
</body>
</html>