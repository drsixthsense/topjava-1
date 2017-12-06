package ru.javawebinar.topjava.service;


import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;

@ActiveProfiles({Profiles.DATAJPA, Profiles.POSTGRES_DB})
public class UserServiceDataJpaTest extends UserServiceTest {

    @Test
    public void UserByIdWithMeals(){
        User user = service.getByIdWithMeals(100000);
        if(user.getMeals()!=null) {
            user.getMeals().forEach(meal -> System.out.println(meal));
        }
    }

}
