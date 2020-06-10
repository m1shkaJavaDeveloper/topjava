package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealsCRUD {

    void add(Meal meal);

    void delete(int mealId);

    void update(Meal meal);

    Meal get(int mealId);

    List<Meal> getAll();
}
