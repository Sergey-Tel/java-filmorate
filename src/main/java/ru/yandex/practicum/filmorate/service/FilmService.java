package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.Interface.FilmInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmService implements FilmInterface {

    private final Map<Integer, Film> films = new HashMap<>();

    @Override
    public Film createFilm(int id, Film film) {
        film.setId(id);
        films.put(id, film);
        return film;
    }

    @Override
    public Film updateFilm(int id, Film film) {
        films.put(id, film);
        return film;
    }

    @Override
    public List<Film> findAllFilms() {
        return new ArrayList<>(films.values());
    }

    public boolean isContains(int id) {
        return films.containsKey(id);
    }
}
