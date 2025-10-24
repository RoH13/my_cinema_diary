<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Мой Кинодневник</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background: #80577e;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .menu {
            display: flex;
            flex-direction: column;
            gap: 10px;
            margin: 30px 0;
        }
        .menu a {
            display: block;
            padding: 15px;
            background: #a2b0d6;
            color: white;
            text-decoration: none;
            text-align: center;
            border-radius: 5px;
        }
        .menu a:hover {
            background: #45a049;
        }
        .stats {
            display: flex;
            justify-content: space-around;
            margin: 20px 0;
            padding: 20px;
            background: #e9ecef;
            border-radius: 5px;
        }
        .stat-item {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>🎬 Мой Кинодневник</h1>
    <p style="text-align: center;">Управление вашей коллекцией фильмов и сериалов</p>

    <div class="menu">
        <a href="movies?action=showAddForm">➕ Добавить фильм</a>
        <a href="series?action=showAddForm">📺 Добавить сериал</a>
        <a href="movies?action=list">🎞️ Список фильмов</a>
        <a href="series?action=list">📡 Список сериалов</a>
    </div>

    <div style="text-align: center; margin-top: 30px;">
        <p>Добро пожаловать! Начните с добавления первого фильма или сериала.</p>
    </div>
</div>
</body>
</html>