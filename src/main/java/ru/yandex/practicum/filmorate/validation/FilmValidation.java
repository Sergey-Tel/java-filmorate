package ru.yandex.practicum.filmorate.validation;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

@Slf4j
public class FilmValidation {
    String exeprionMessage;

    public Film filmValidation(Film film) {
        if (film.getName().isEmpty()) {
            exeprionMessage = "название не может быть пустым";
            log.debug(exeprionMessage);
            throw new ValidationException(exeprionMessage);
        }
        if (film.getDescription().length() > 200) {
            exeprionMessage = "максимальная длина описания — 200 символов";
            log.debug(exeprionMessage);
            throw new ValidationException(exeprionMessage);
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            exeprionMessage = "дата релиза — не раньше 28 декабря 1895 года";
            log.debug(exeprionMessage);
            throw new ValidationException(exeprionMessage);
        }
        if (film.getDuration() <= 0) {
            exeprionMessage = "продолжительность фильма должна быть положительной";
            log.debug(exeprionMessage);
            throw new ValidationException(exeprionMessage);
        }
        return film;
    }
}
