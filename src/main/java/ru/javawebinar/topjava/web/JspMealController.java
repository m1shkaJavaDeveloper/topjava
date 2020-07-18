package ru.javawebinar.topjava.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
public class JspMealController {
    @Autowired
    private MealRestController mealController;

    @GetMapping("/")
    public String getAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("meals", mealController.getAll());
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        return "meals";
    }

    @GetMapping("/delete")
    public String deleteMeal(HttpServletRequest request) {
        int id=Integer.parseInt(request.getParameter("id"));
        mealController.delete(id);
        return "meals";
    }

    @GetMapping("/create")
    public String createMeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        mealController.create(meal);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
        return "meals";
    }

    @GetMapping("/update")
    public String updateMeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Meal meal = mealController.get(Integer.parseInt(request.getParameter("id")));
        mealController.create(meal);
        request.setAttribute("meal", meal);
        request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
        return "meals";
    }

    @GetMapping("/filter")
    public String filterMeals(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        request.setAttribute("meals", mealController.getBetween(startDate, startTime, endDate, endTime));
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        return "meals";
    }


}
