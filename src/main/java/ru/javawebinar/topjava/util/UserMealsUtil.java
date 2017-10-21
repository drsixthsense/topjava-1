package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with correctly exceeded field
        List<UserMealWithExceed> result = new ArrayList<>();

        List<LocalDate> allDates = mealList.stream()
                .map(dates -> dates.getDateTime().toLocalDate())
                .distinct()
                .collect(Collectors.toList());

        List<Boolean> isExceedingInDates = allDates.stream().map(meals -> {
            int callories = 0;
            for(UserMeal m : mealList){
                if(m.getDateTime().toLocalDate().equals(meals)){
                    callories+=m.getCalories();
                }
            }
            return callories>2000;
        }).collect(Collectors.toList());

        List<UserMealWithExceed> preResult = new ArrayList<>();

        for (int i = 0; i < allDates.size(); i++) {
            for(UserMeal m : mealList){
                if(m.getDateTime().toLocalDate().equals(allDates.get(i))){
                    UserMealWithExceed e = new UserMealWithExceed(m.getDateTime(), m.getDescription(), m.getCalories(), isExceedingInDates.get(i));
                    preResult.add(e);
                }
            }
        }

        result = preResult.stream()
                .filter(meal -> meal.getDateTime().toLocalTime().isAfter(startTime)&&meal.getDateTime().toLocalTime().isBefore(endTime))
                .collect(Collectors.toList());

        System.out.println();


        return result;
    }
}
