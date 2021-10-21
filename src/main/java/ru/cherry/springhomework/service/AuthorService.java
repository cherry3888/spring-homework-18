package ru.cherry.springhomework.service;

import ru.cherry.springhomework.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    Author addAuthor(String name);
    Author getAuthor(String name);
    Author editAuthor(Author author);
    boolean deleteAuthor(Long id);
}
