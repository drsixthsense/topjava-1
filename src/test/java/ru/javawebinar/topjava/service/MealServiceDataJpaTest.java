package ru.javawebinar.topjava.service;


import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;

@ActiveProfiles({Profiles.DATAJPA, Profiles.POSTGRES_DB})
public class MealServiceDataJpaTest extends MealServiceTest {

    @Test
    public void getMealWithIdAndCompareUsers(){
        Meal meal1 = service.get(100003, 100000);
        Meal meal2 = service.get(100004, 100000);

        UserTestData.assertMatch(meal1.getUser(), meal2.getUser());

    }

}
