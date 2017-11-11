package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MealRepository {

    private static List<Meal> meals = new LinkedList<>(Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    ));


    public static List<Meal> getMeals(){
        return meals;
    }

    public static void addMeal(LocalDateTime dateTime, String description, int calories){
        meals.add(new Meal(dateTime, description, calories));
    }

    public static void removeMeal(int id){
        for (Iterator<Meal> i = meals.iterator(); i.hasNext(); ) {
            Meal value =  i.next();
            if(value.getId() == id) {
                i.remove();
            }
        }
    }
}
