package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id = :user_id")
    int delete(@Param("id") int id, @Param("user_id") int userId);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Override
    Optional<Meal> findById(Integer id);

    @Override
    List<Meal> findAll(Sort sort);

    List<Meal> findAllByUserId(int user_id, Sort sort);

    Meal findMealByUserIdAndId(Integer id, int user_id);

    List<Meal> findAllByUserIdAndDateTimeBetween(int user_id, LocalDateTime from, LocalDateTime to, Sort sort);

    @Query("SELECT m FROM Meal m left join fetch m.user WHERE m.id = :id AND m.user.id = :user_id ")
    Meal findMealByIdWithUsers(@Param("id") int id, @Param("user_id") int user_id);
}
