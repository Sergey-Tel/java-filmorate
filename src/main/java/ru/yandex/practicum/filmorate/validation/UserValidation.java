package ru.yandex.practicum.filmorate.validation;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Slf4j
public class UserValidation {

    String exceptionMessage;

    public User userValidation(User user) {
        if (user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            exceptionMessage = "электронная почта не может быть пустой и должна содержать символ @";
            log.debug(exceptionMessage);
            throw new ValidationException(exceptionMessage);
        }
        if (user.getLogin().isBlank()) {
            exceptionMessage = "логин не может быть пустым и содержать пробелы";
            log.debug(exceptionMessage);
            throw new ValidationException(exceptionMessage);
        }
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            exceptionMessage = "дата рождения не может быть в будущем";
            log.debug(exceptionMessage);
            throw new ValidationException(exceptionMessage);
        }
        return user;
    }
}
