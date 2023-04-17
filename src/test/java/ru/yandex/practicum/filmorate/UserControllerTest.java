package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.UserController;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validation.ValidationException;

import java.time.LocalDate;

class UserControllerTest {

    UserController userController = new UserController();

    @Test()
    void checkUserEmail() {
        Assertions.assertThrows(ValidationException.class, () -> {
            userController.createUser(User.builder()
                    .id(1)
                    .email("myMail.ru")
                    .login("lg")
                    .name("Sergo")
                    .birthday(LocalDate.now()).build());

        });
    }

    @Test
    void checkUserName() {
        Assertions.assertThrows(ValidationException.class, () -> {
            userController.createUser(User.builder()
                    .id(1)
                    .email("myMail@.ru")
                    .login("lg")
                    .name("")
                    .birthday(LocalDate.now()).build());
        });
    }

    @Test
    void checkUserLogin() {
        Assertions.assertThrows(ValidationException.class, () -> {
            userController.createUser(User.builder()
                    .id(1)
                    .email("myMail@.ru")
                    .login(" ")
                    .name("Sergo")
                    .birthday(LocalDate.now()).build());
        });
    }

    @Test
    void checkUserBirthday() {
        Assertions.assertThrows(ValidationException.class, () -> {
            userController.createUser(User.builder()
                    .id(1)
                    .email("myMail@.ru")
                    .login("lg")
                    .name("Sergo")
                    .birthday(LocalDate.now().plusDays(12)).build());
        });
    }
}
