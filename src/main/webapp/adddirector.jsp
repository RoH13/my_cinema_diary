<%--
  Created by IntelliJ IDEA.
  User: raman
  Date: 10/30/25
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new director</title>
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
<form action = 'director' method = 'post'>

    <div class="form-group">
        <label for="firstname">Имя:</label>
        <input type='text' id="firstname" name='firstname'>
    </div>

    <div class="form-group">
        <label for="secondname">Фамилия:</label>
        <input type='text' id="secondname" name='secondname'>
    </div>


    <input type='submit' value='Добавить режиссера'>
</form>

<div style="margin-top: 20px;">
    <a href="director?action=list">← Назад к списку режиссеров</a>
</div>
</body>

