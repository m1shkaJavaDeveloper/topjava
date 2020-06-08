<%@ page import="ru.javawebinar.topjava.util.TimeUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ru">
<head>
    <style>
        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            border: 1px solid #f2f2f2;
        }

        th, td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even) {
            background-color: #ddd;
        }
    </style>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <thead>
    <tr>
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${meals}">
        <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.model.MealTo"/>
        <tr style="${meal.excess ? "color: red" : "color: green"}">
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
<%--            <td><a href="meals?action=edit&mealId=${meal.id}">Update</a></td>--%>
<%--            <td><a href="meals?action=delete&mealId=${meal.id}">Delete</a></td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
