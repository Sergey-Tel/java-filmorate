package ru.yandex.practicum.filmorate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.NotEmpty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.Set;

@SpringBootTest
public class FilmControllerTest {
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @Test()
    @DisplayName("Проверка name на ошибки")
    void checkFilmName() {
        Film film1 = createFilm(" ", "description", LocalDate.EPOCH, 10);
        Film film2 = createFilm(null, "description", LocalDate.EPOCH, 10);

        Assertions.assertAll(
                () -> Assertions.assertTrue(checkFilm(film1, "название не может быть пустым")),
                () -> Assertions.assertTrue(checkFilm(film2, "название не может быть пустым"))
        );
    }

    @Test()
    @DisplayName("Проверка ReleaseDate на ошибки")
    void checkFilmReleaseDate() {
        Film film = createFilm("nameOfFilm", "description", LocalDate.of(1800,10,10), 10);
        Assertions.assertTrue(checkFilm(film, "Дата релиза не может быть раньше 28 декабря 1895 года"));
    }

    @Test()
    @DisplayName("Проверка Description на ошибки")
    void checkFilmDescription() {
        Film film = createFilm("nameOfFilm", new String(new char[200]), LocalDate.EPOCH, 5);

        Assertions.assertTrue(checkFilm(film, "максимальная длина описания — 200 символов"));

    }

    @DisplayName("Проверка Duration на ошибки")
    @Test()
    void checkFilmDuration() {
        Film film = createFilm("nameOfFilm", "description", LocalDate.EPOCH, -10);

        Assertions.assertTrue(checkFilm(film, "продолжительность фильма должна быть положительной"));
    }

    private Film createFilm(String name, String description, LocalDate releaseDate, int duration) {
        return Film.builder()
                .name(name)
                .description(description)
                .releaseDate(releaseDate)
                .duration(duration)
                .build();
    }

    private boolean checkFilm(Film film, @NotEmpty String message) {
        Set<ConstraintViolation<Film>> errors = VALIDATOR.validate(film);
        return errors.stream().map(ConstraintViolation::getMessage).anyMatch(message::equals);
    }
}
