package ru.javawebinar.topjava.service.jdbc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import java.util.List;

import static ru.javawebinar.topjava.Profiles.JDBC;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.assertMatch;

@ActiveProfiles(JDBC)
public class JdbcUserServiceTest extends AbstractUserServiceTest {

    @Override
    @Before
    public void setUp(){
        cacheManager.getCache("users").clear();
    }

    @Override
    @Test
    public void getAll() throws Exception {
        List<User> all = service.getAll();
        assertMatch(all, ADMIN, USER);
    }

}