package ru.javawebinar.topjava.web.meal;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/user/meals";

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation (@RequestBody Meal meal) {
        Meal created = super.create(meal);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Meal meal, @PathVariable("id") int id) {
        super.update(meal, id);
    }

    @GetMapping(value = "/{startDate}/{startTime}/{endDate}/{endTime}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetweenForRest (@PathVariable("startDate") String startDate, @PathVariable("startTime") String startTime , @PathVariable("endDate") String endDate, @PathVariable("endTime") String endTime) {
/*        if(startDate==null) startDate = LocalDate.MIN;
        if(endDate==null) startDate = LocalDate.MAX;
        if(startTime == null) startTime = LocalTime.MIN;
        if(endTime == null) endTime = LocalTime.MAX;*/
        LocalDate sDate, eDate;
        LocalTime sTime, eTime;

        if(startDate.equals("null")){
            sDate = LocalDate.MIN;
        } else { sDate = LocalDate.parse(startDate); }
        if(startTime.equals("null")){
            sTime = LocalTime.MIN;
        } else { sTime = LocalTime.parse(startTime); }
        if(endDate.equals("null")){
            eDate = LocalDate.MAX;
        } else { eDate = LocalDate.parse(endDate); }
        if(startTime.equals("null")){
            eTime = LocalTime.MAX;
        } else { eTime = LocalTime.parse(endTime); }

        return super.getBetween(sDate, sTime, eDate, eTime);
    }
}