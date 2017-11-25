package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_ID = START_SEQ+2;
    public static final Meal MEAL1 = new Meal( MEAL_ID, LocalDateTime.parse("2017-11-04 07:30:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL_ID+1, LocalDateTime.parse("2017-11-04 12:40:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL_ID+2, LocalDateTime.parse("2017-11-04 21:10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Ужин", 510);
    public static final Meal MEAL4 = new Meal(MEAL_ID+3, LocalDateTime.parse("2017-11-05 08:40:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Завтрак", 510);
    public static final Meal MEAL5 = new Meal(MEAL_ID+4, LocalDateTime.parse("2017-11-05 14:10:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Обед", 510);
    public static final Meal MEAL6 = new Meal(MEAL_ID+5, LocalDateTime.parse("2017-11-05 19:20:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Ужин", 490);
/*





    public static final Meal MEAL7 = new Meal(MEAL_ID+7, LocalDateTime.parse("2017-11-04 07:30:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Завтрак", 500);
    public static final Meal MEAL8 = new Meal(MEAL_ID+8, LocalDateTime.parse("2017-11-04 12:40:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Обед", 1000);
    public static final Meal MEAL9 = new Meal(MEAL_ID+9, LocalDateTime.parse("2017-11-04 21:10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Ужин", 380);
    public static final Meal MEAL10 = new Meal(MEAL_ID+10, LocalDateTime.parse("2017-11-05 08:40:0", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Завтрак", 510);
    public static final Meal MEAL11 = new Meal(MEAL_ID+11, LocalDateTime.parse("2017-11-05 14:10:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Обед", 900);
    public static final Meal MEAL12 = new Meal(MEAL_ID+12, LocalDateTime.parse("2017-11-05 19:20:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "Ужин", 600);
*/

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "userId");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("userId").isEqualTo(expected);
    }
}
