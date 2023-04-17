package ru.yandex.practicum.filmorate.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validation.FilmValidation;
import ru.yandex.practicum.filmorate.validation.ValidationException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final Map<Integer, Film> films = new HashMap<>();
    FilmValidation filmValidation = new FilmValidation();
    private int filmId = 1;

    @GetMapping
    public Collection<Film> findAll() {
        log.debug("Получение списка всех доступных фильмов");
        return films.values();
    }
    @GetMapping
    public String homePage() {
        log.debug("Получен запрос GET /home.");
        return "приложение запущено";
    }
    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) {
        log.debug("Создание нового фильма");
        filmValidation.filmValidation(film);
        film.setId(filmId);
        films.put(filmId, film);
        filmId++;
        return film;
    }

    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        log.debug("зменение данных фильма");
        if (films.containsKey(film.getId())) {
            films.put(film.getId(), film);
        } else {
            throw new ValidationException("Фильм для изменения данных отсутствует");
        }
        return film;
    }
}
