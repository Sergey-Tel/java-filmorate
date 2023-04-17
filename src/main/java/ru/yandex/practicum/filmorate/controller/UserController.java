package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.UserValidation;
import ru.yandex.practicum.filmorate.validation.ValidationException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();
    UserValidation userValidation = new UserValidation();

    private int userId = 1;

    @GetMapping
    public Collection<User> findAll() {
        log.debug("Полечение списка всех пользователей");
        return users.values();
    }


    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        log.debug("Создание нового пользователя");
        userValidation.userValidation(user);
        user.setId(userId);
        users.put(userId, user);
        userId++;
        return user;
    }

    @PutMapping
    public User updateUser (@Valid @RequestBody User user) {
        log.debug("Изменение данных пользователя");
        if (users.containsKey(user.getId())) {
            users.put(user.getId(),user);
        } else {
            throw new ValidationException("Пользователь для зменения данных отсутствует");
        }
        return user;
    }
}
