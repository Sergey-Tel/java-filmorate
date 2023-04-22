package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.UserUpdateException;
import ru.yandex.practicum.filmorate.exception.UserValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service = new UserService();

    private int userId;
    private int getId() {
        return ++userId;
    }

    @GetMapping
    public Collection<User> findAllUsers() {
        log.debug("Полечение списка всех пользователей");
        return service.findAllUsers();
    }


    @PostMapping
    public User createUser(@Valid @RequestBody User user,BindingResult bindingResult) {
       validateUser(bindingResult);
        int userId = getId();
        User userForSave = service.createUser(userId,user);
        log.debug("Пользователем с новым id - " + userId + " добавлен");
        return userForSave;
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user,BindingResult bindingResult) {
        validateUser(bindingResult);
        int userId = user.getId();

        if (!service.isContains(userId)) {
            log.debug("Данного пользователся не существует");
            throw new UserUpdateException();
        }
        User userForUpdate = service.updateUser(userId,user);
        log.debug("Пользователь с номером - " + userId + " был обновлен");
        return userForUpdate;
    }

    private void validateUser(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("email")) {
            log.debug("Ошибка валидации пользователя. Неверный email");
            throw new UserValidationException();
        }

        if (bindingResult.hasFieldErrors("birthday")) {
            log.debug("Ошибка валидации пользователя. Дата рождения не может быть в будущем");
            throw new UserValidationException();
        }

        if (bindingResult.hasFieldErrors("login")) {
            log.debug("Ошибка валидации пользователя. Логин не может быть пустым и содержать пробелы");
            throw new UserValidationException();
        }
    }
}
