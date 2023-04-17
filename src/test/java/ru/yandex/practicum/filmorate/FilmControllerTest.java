package ru.yandex.practicum.filmorate;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.FilmController;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.ValidationException;

import java.time.LocalDate;

public class FilmControllerTest {
    FilmController filmController = new FilmController();

    @Test()
    void checkFilmName() {
        Assertions.assertThrows(ValidationException.class, () -> {
            filmController.createFilm(Film.builder()
                    .id(1)
                    .name("")
                    .description("desc")
                    .releaseDate(LocalDate.EPOCH)
                    .duration(100).build());
        });
    }

    @Test()
    void checkFilmReleaseDate() {
        Assertions.assertThrows(ValidationException.class, () -> {
            filmController.createFilm(Film.builder()
                    .id(1)
                    .name("abc")
                    .description("desc")
                    .releaseDate(LocalDate.of(1800,10,20))
                    .duration(100).build());
        });
    }
    @Test()
    void checkFilmDescription() {
        String description = StringUtils.repeat("a", 250);
        Assertions.assertThrows(ValidationException.class, () -> {
            filmController.createFilm(Film.builder()
                    .id(1)
                    .name("abc")
                    .description(description)
                    .releaseDate(LocalDate.EPOCH)
                    .duration(100).build());
        });
    }
    @Test()
    void checkFilmDuration() {
        Assertions.assertThrows(ValidationException.class, () -> {
            filmController.createFilm(Film.builder()
                    .id(1)
                    .name("abc")
                    .description("abc")
                    .releaseDate(LocalDate.EPOCH)
                    .duration(-10).build());
        });
    }
}
