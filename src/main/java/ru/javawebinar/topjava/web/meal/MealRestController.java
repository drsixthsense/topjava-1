package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MealRestController {
    private static final Logger log = getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll(AuthorizedUser.id());
    }

    public Meal get(int id) {
        log.info("get {}", id, AuthorizedUser.id());
        return service.get(id, AuthorizedUser.id());
    }

    public Meal save(Meal meal) {
        log.info("create {}", meal);
        return service.save(meal, AuthorizedUser.id());
    }

    public boolean delete(int id) {
        log.info("delete {}", id, AuthorizedUser.id());
        return service.delete(id, AuthorizedUser.id());
    }
}