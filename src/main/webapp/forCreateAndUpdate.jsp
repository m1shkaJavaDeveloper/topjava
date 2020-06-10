<%--
  Created by IntelliJ IDEA.
  User: Михаил
  Date: 09.06.2020
  Time: 21:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create your meal</title>
</head>
<body>
    <div align="center">
        <form method="POST" action="meals" name="formCreateMeal">
            <jsp:useBean id="meal" scope="request" type="ru.javawebinar.topjava.model.Meal"/>
                DateTime: <input type="dateTime" name="dateTime" value="${meal.dateTime}"><br/>
                Description: <input type="text" name="description" value="${meal.description}"><br/>
                Calories: <input type="text" name="calories" value="${meal.calories}"><br/>
                <input type="hidden" name="mealId" value="${meal.id}">
                <input type="submit" value="Save">
        </form>
    </div>
</body>
</html>
