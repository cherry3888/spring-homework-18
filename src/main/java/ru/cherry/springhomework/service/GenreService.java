package ru.cherry.springhomework.service;

import ru.cherry.springhomework.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();
    Genre addGenre(String name);
    Genre getGenre(String name);
    Genre editGenre(Genre genre);
    boolean deleteGenre(Long id);
}
