package ru.javawebinar.topjava.web.meal;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/ajax/profile/meals")
public class MealAjaxController extends AbstractMealController {
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("description") String description,
                               @RequestParam("calories") int calories)
                                {

        Meal meal = new Meal(LocalDateTime.now(), description, calories);
        if (meal.isNew()) {
            super.create(meal);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "/filter")
    public List<MealWithExceed> getBetweenForRest(
            @RequestParam("startDate")LocalDate startDate,
            @RequestParam("startTime")LocalTime startTime,
            @RequestParam("endDate")LocalDate endDate,
            @RequestParam("endTime")LocalTime endTime){

        return getBetween(startDate, startTime, endDate, endTime);
    }

}
