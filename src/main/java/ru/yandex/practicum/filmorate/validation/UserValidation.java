package ru.yandex.practicum.filmorate.validation;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Slf4j
public class UserValidation {

    String exeprionMessage;

    public User userValidation(User user) {
        if (user.getEmail().isBlank() || !user.getEmail().contains("@")) {
            exeprionMessage = "электронная почта не может быть пустой и должна содержать символ @";
            log.debug(exeprionMessage);
            throw new ValidationException(exeprionMessage);
        }
        if (user.getLogin().isBlank()) {
            exeprionMessage = "логин не может быть пустым и содержать пробелы";
            log.debug(exeprionMessage);
            throw new ValidationException(exeprionMessage);
        }
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            exeprionMessage = "дата рождения не может быть в будущем";
            log.debug(exeprionMessage);
            throw new ValidationException(exeprionMessage);
        }
        return user;
    }
}
