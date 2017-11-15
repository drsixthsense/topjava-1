package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal m : MealsUtil.MEALS) {
            this.save(m, m.getUserID());
        }
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserID(userId);
            repository.put(meal.getId(), meal);
        } else {
            if(repository.get(meal.getId()).getUserID()==userId) {
                repository.put(meal.getId(), meal);
            } else {
                throw new NotFoundException("Can't update meal, inproper owner");
            }
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal toDelete;
        try {
            toDelete = repository.get(id);
            if(toDelete.getUserID()==userId) {
                repository.remove(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        Meal mealToGet;
        try {
            mealToGet = repository.get(id);
            if(mealToGet.getUserID()==userId) {
                return mealToGet;
            } else {
                throw new NotFoundException("Meal to delete was not found or does not belong to user");
            }
        } catch (NotFoundException e) {
            return null;
        }
    }

    @Override
    public Collection<Meal> getAll(int userId) {

        List<Meal> meals = repository.values().stream().
                filter(p -> p.getUserID() == userId).collect(Collectors.toList());
        meals.sort(new Comparator<Meal>() {
            @Override
            public int compare(Meal o1, Meal o2) {
                return o1.getDateTime().isAfter(o2.getDateTime()) ? -1 : 1;
            }
        });
        return meals;
    }
}

