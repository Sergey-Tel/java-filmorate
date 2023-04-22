package ru.yandex.practicum.filmorate.service.Interface;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmInterface {
    Film createFilm(int id,Film film);
    Film updateFilm(int id,Film film);
    List<Film> findAllFilms();
}
