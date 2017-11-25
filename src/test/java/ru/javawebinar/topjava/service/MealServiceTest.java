package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    MealService mealService;

    @Test
    public void get() throws Exception {
        Meal meal = mealService.get(100002, 100000);
        assertMatch(meal, MEAL1);
    }

    @Test (expected = NotFoundException.class)
    public void getNotOwn() throws Exception {
        mealService.get(MEAL1.getId(), 100001);
    }

    @Test (expected = NotFoundException.class)
    public void deleteNotOwn() throws Exception {
        mealService.delete(100002, 100001);
    }

    @Test
    public void delete() throws Exception {
        mealService.delete(MEAL1.getId(), 100000);
        assertMatch(mealService.getAll(100000), MEAL2, MEAL3, MEAL4, MEAL5, MEAL6);
    }

    @Test
    public void getBetweenDates() throws Exception {
        Meal m1 = new Meal( MEAL_ID, LocalDateTime.parse("2017-11-04 07:30:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Завтрак", 500);
        Meal m2 = new Meal( MEAL_ID+1, LocalDateTime.parse("2017-11-04 07:30:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Завтрак", 500);
        System.out.println(MEAL1);
        System.out.println(MEAL2);
        System.out.println(m1);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> meals = mealService.getBetweenDateTimes(LocalDateTime.of(2017, 11, 04, 00, 00),
                LocalDateTime.of(2017, 11, 04, 23, 59), 100000);
        assertMatch(meals, MEAL1, MEAL2, MEAL3);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> all = mealService.getAll(100000);
        assertMatch(all, MEAL1, MEAL2, MEAL3, MEAL4, MEAL5, MEAL6);
    }

    @Test
    public void update() throws Exception {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("Updated");
        updated.setCalories(666);
        mealService.update(updated, 100000);
        assertMatch(mealService.get(MEAL1.getId(), 100000), updated);
    }

    @Test (expected = NotFoundException.class)
    public void updateNotOwn() throws Exception {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("Updated");
        updated.setCalories(666);
        mealService.update(updated, 100001);
    }

    @Test
    public void create() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.now(), "Завтрак", 500);
        Meal meal = mealService.create(newMeal, 100000);
        newMeal.setId(meal.getId());
        assertMatch(mealService.getAll(100000), MEAL1,  MEAL2, MEAL3, MEAL4, MEAL5, MEAL6, newMeal);
    }

}