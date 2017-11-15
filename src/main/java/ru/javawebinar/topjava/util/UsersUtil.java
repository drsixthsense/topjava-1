package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null, "anrew", "admin@gmail.com", "password", Role.ROLE_ADMIN, Role.values()),
            new User(null, "vasya", "vasya@gmail.com", "password1", Role.ROLE_USER, Role.values()),
            new User(null, "Olka", "ka4ka@gmail.com", "password2", Role.ROLE_USER, Role.values()),
            new User(null, "Klo", "klo@gmail.com", "password3", Role.ROLE_USER, Role.values()),
            new User(null, "csaba", "csabiko@gmail.com", "password3", Role.ROLE_USER, Role.values())
    );
}
