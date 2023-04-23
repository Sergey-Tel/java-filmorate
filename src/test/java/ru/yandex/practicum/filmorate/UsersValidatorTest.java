package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

public class UsersValidatorTest {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("Проверка невалидности login")
    void addUserWithFailLogin() {
        User user = createUser("qwerty@Yandex.ru", " ", "Sergo", "2000-10-10");
        User user1 = createUser("qwerty@Yandex.ru", null, "Sergo", "2000-10-10");
        User user2 = createUser("qwerty@Yandex.ru", "QW E", "Sergo", "2000-10-10");
        User user3 = createUser("qwerty@Yandex.ru", "    ", "Sergo", "2000-10-10");

        Assertions.assertAll(
                () -> Assertions.assertTrue(requestUser(user, "Логин не должен содержать пробелы")),
                () -> Assertions.assertFalse(requestUser(user, "Логин не может быть пустым")),
                () -> Assertions.assertTrue(requestUser(user1, "Логин не может быть пустым")),
                () -> Assertions.assertTrue(requestUser(user2, "Логин не должен содержать пробелы")),
                () -> Assertions.assertTrue(requestUser(user3, "Логин не должен содержать пробелы"))
        );
    }

    @Test
    @DisplayName("Проверка невалидности email")
    void addUserWithFailEmail() {
        User user = createUser("qwertyYandex.ru", "login", "Sergo", "2000-10-10");
        User user1 = createUser(" ", "login", "Sergo", "2000-10-10");
        User user2 = createUser(null, "login", "Sergo", "2000-10-10");
        User user3 = createUser("qwertyYandex@", "login", "Sergo", "2000-10-10");

        Assertions.assertAll(
                () -> Assertions.assertTrue(requestUser(user, "Email введен с ошибкой")),
                () -> Assertions.assertTrue(requestUser(user1, "Email не может быть пустым")),
                () -> Assertions.assertTrue(requestUser(user2, "Email не может быть пустым")),
                () -> Assertions.assertTrue(requestUser(user3, "Email введен с ошибкой"))
        );
    }

    @Test
    @DisplayName("Проверка невалидности birthday")
    void addUserWithFailBirthday() {
        User user = createUser("qwert@Yandex.ru", "login", "Sergo", "2050-10-10");
        Assertions.assertTrue(requestUser(user, "Дата рождения не может быть в будущем"));
    }

    private User createUser(String email, String login, String name, String birthday) {
        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setName(name);
        user.setBirthday(LocalDate.parse(birthday));
        return user;
    }

    private boolean requestUser(User user, @NotEmpty String message) {
        Set<ConstraintViolation<User>> errors = VALIDATOR.validate(user);
        return errors.stream().map(ConstraintViolation::getMessage).anyMatch(message::equals);
    }
}
