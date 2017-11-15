package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;


    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public boolean delete(int id, int userId) throws NotFoundException {
        return repository.delete(id, userId);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return repository.get(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return (List<Meal>)repository.getAll(userId);
    }
}