package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<Meal> getAll(int userId) {
        log.info("getAll");
        return service.getAll(userId);
    }

    public Meal get(int id, int userId) {
        log.info("get {}", id, userId);
        return service.get(id, userId);
    }

    public Meal save(Meal meal, int userId) {
        log.info("create {}", meal);
        return service.save(meal, userId);
    }

    public boolean delete(int id, int userId) {
        log.info("delete {}", id, userId);
        return service.delete(id, userId);
    }
}