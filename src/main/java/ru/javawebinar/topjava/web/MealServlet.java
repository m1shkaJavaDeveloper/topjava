package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealsCRUDMethods;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private MealsCRUDMethods dao = MealsUtil.createDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("MealServlet doGet");

        List<Meal> meals = dao.getAll();
        List<MealTo> mealsTo = MealsUtil.filteredByStreams(meals, LocalTime.MIN, LocalTime.MAX, 2000);

        String action = request.getParameter("action");
        String forward;
        String mealsJsp = "meals.jsp";
        String forCreateAndUpdateJsp = "forCreateAndUpdate.jsp";

        switch ((action == null) ? "" : action) {
            case "delete": {
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                dao.delete(mealId);
                forward = mealsJsp;
                request.setAttribute("meals", mealsTo);
                break;
            }
            case "update": {
                forward = forCreateAndUpdateJsp;
                int mealId = Integer.parseInt(request.getParameter("mealId"));
                Meal meal = dao.get(mealId);
                request.setAttribute("meal", meal);
                break;
            }
            case "create": {
                forward = forCreateAndUpdateJsp;
                break;
            }
            default: {
                forward = mealsJsp;
                request.setAttribute("meals", mealsTo);
            }
        }
        request.getRequestDispatcher(forward).forward(request, response);
//        response.sendRedirect("meals.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("MealServlet doPost");
        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String mealId = request.getParameter("mealId");

        Meal meal = new Meal(localDateTime, description, calories);

        if (mealId.equals("")) dao.add(meal);
        else {
            meal.setId(Integer.parseInt(mealId));
            dao.update(meal);
        }
        response.sendRedirect("meals");
    }
}
