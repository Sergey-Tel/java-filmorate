package ru.yandex.practicum.filmorate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotEmpty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;


import java.time.LocalDate;
import java.util.Set;

class UserControllerTest {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @Test()
    @DisplayName("Проверка Email на ошибки")
    void checkUserEmail() {
        User user1 = createUser("qwerty.ru", "qwerty", "qwerty", LocalDate.of(2000,12,12));
        User user2 = createUser(" ", "qwerty", "qwerty", LocalDate.of(2000,12,12));


        Assertions.assertAll(
                () -> Assertions.assertTrue(requestUser(user1, "Email введен с ошибкой")),
                () -> Assertions.assertTrue(requestUser(user2, "Email не может быть пустым"))
        );
    }

    @Test
    @DisplayName("Проверка Name на ошибки")
    void checkUserName() {
        User user = createUser("qwert@qwert.ru", "qwerty", "", LocalDate.now().plusMonths(1));
        Assertions.assertEquals(user.getName(),user.getLogin());
    }

    @Test
    @DisplayName("Проверка Login на ошибки")
    void checkUserLogin() {
        User user1 = createUser("qwer@qwer.ru", " ", "Sergo", LocalDate.of(2000,12,12));
        User user2 = createUser("asd2@Yandex.ru", "qwer q", "Sergo", LocalDate.of(2000,12,12));


        Assertions.assertAll(
                () -> Assertions.assertTrue(requestUser(user1, "Логин не должен содержать пробелы")),
                () -> Assertions.assertFalse(requestUser(user1, "Логин не должен быть пустым")),
                () -> Assertions.assertTrue(requestUser(user2, "Логин не должен содержать пробелы"))
        );
    }

    @Test
    @DisplayName("Проверка Birthday на ошибки")
    void checkUserBirthday() {
        User user = createUser("qwert@qwert.ru", "qwerty", "Sergo", LocalDate.now().plusMonths(1));
        Assertions.assertTrue(requestUser(user, "Дата рождения должна быть в прошлом"));
    }

    private User createUser(String email, String login, String name, LocalDate birthday) {
        return User.builder()
                .name(name)
                .login(login)
                .email(email)
                .birthday(birthday)
                .build();
    }

    private boolean requestUser(User user, @NotEmpty String message) {
        Set<ConstraintViolation<User>> errors = VALIDATOR.validate(user);
        return errors.stream().map(ConstraintViolation::getMessage).anyMatch(message::equals);
    }
}
