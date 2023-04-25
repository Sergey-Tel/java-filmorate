package ru.yandex.practicum.filmorate.storage.InterfecesStorage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmStorage {
    void add(Integer id, Film film);

    void remove(Integer id);

    Film get(Integer id);

    List<Film> getAll();

    boolean isContains(Integer id);
}