package ru.cherry.springhomework.service;

import ru.cherry.springhomework.domain.Author;
import ru.cherry.springhomework.domain.Book;
import ru.cherry.springhomework.domain.Genre;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getById(Long id);
    Book save(Book book);

    Book addBook(String title, String authorName, String genreName);
    List<Book> findBook(String title);
    Book editBook(Long id, String newTitle);
    boolean deleteBook(Long id);
}
