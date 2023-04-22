package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.FilmUpdateException;
import ru.yandex.practicum.filmorate.exception.FilmValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService service = new FilmService();
    private int filmId;

    private int getId() {
        return ++filmId;
    }

    @GetMapping
    public Collection<Film> findAllFilms() {
        log.debug("Получение списка всех доступных фильмов");
        return service.findAllFilms();
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film, BindingResult bindingResult) {
        validateFilm(bindingResult);
        int FilmId = getId();
        Film filmForSave = service.createFilm(FilmId, film);
        log.debug("Добавлен новый фильм. Номер фильма - " + FilmId);
        return filmForSave;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film, BindingResult bindingResult) {
        validateFilm(bindingResult);

        int filmId = film.getId();

        if (!service.isContains(filmId)) {
            log.debug("Ошибка обновления фильма, данный фильм отсутствует в базе данных");
            throw new FilmUpdateException();
        }
        service.updateFilm(filmId, film);
        log.debug("Фильм с номером - " + filmId + " Отсутствует");
        return film;
    }

    private void validateFilm(BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors("name")) {
            log.debug("Ошибка валидации фильма. Название фильма не может быть пустым");
            throw new FilmValidationException();
        }

        if (bindingResult.hasFieldErrors("description")) {
            log.debug("Ошибка валидации фильма. Описание не может превышать 200 символов");
            throw new FilmValidationException();
        }

        if (bindingResult.hasFieldErrors("releaseDate")) {
            log.debug("Ошибка валидации фильма. Дата релиза не может быть раньше 28 декабря 1895 года");
            throw new FilmValidationException();
        }

        if (bindingResult.hasFieldErrors("duration")) {
            log.debug("Ошибка валидации фильма. Продолжительность фильма не может быть отрицательным");
            throw new FilmValidationException();
        }
    }
}
