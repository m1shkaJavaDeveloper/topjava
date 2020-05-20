package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.TimeUtil.isBetweenHalfOpen;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);
        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }
    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dateCalories = new HashMap<>();
        for (UserMeal m : meals)
            dateCalories.merge(m.getDateTime().toLocalDate(), m.getCalories(), Integer::sum);
        List<UserMealWithExcess> mealsFilteredByCycles = new ArrayList<>();
        for (UserMeal m : meals) {
            if (isBetweenHalfOpen(m.getDateTime().toLocalTime(), startTime, endTime))
                mealsFilteredByCycles.add(new UserMealWithExcess(m.getDateTime(), m.getDescription(), m.getCalories(), dateCalories.get(m.getDateTime().toLocalDate()) > caloriesPerDay));
        }
        return mealsFilteredByCycles;
    }
    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> dateCalories = meals.stream().
                collect(Collectors.groupingBy(m -> m.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));
        return meals.stream().filter(f-> isBetweenHalfOpen(f.getDateTime().toLocalTime(), startTime, endTime)).
                map(m -> new UserMealWithExcess(m.getDateTime(), m.getDescription(), m.getCalories(), dateCalories.get(m.getDateTime().toLocalDate()) > caloriesPerDay)).
                collect(Collectors.toList());
    }
}