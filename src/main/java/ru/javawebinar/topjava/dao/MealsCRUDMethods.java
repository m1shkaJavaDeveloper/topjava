package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealsCRUDMethods implements MealsCRUD{

    final Map<Integer, Meal> idMeal = new ConcurrentHashMap<>();
    private AtomicInteger index = new AtomicInteger();

    @Override
    public void add(Meal meal) {
        Integer id = index.getAndIncrement();
        meal.setId(id);
        idMeal.put(id,meal);
    }

    @Override
    public void delete(int mealId) {idMeal.remove(mealId);}

    @Override
    public void update(Meal meal) {idMeal.replace(meal.getId(), meal);}

    @Override
    public Meal get(int mealId) {return idMeal.get(mealId);}

    @Override
    public List<Meal> getAll() {return new ArrayList<>(idMeal.values());}
}
