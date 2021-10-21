package ru.cherry.springhomework.controller;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.cherry.springhomework.domain.Author;
import ru.cherry.springhomework.domain.Book;
import ru.cherry.springhomework.domain.Comment;
import ru.cherry.springhomework.domain.Genre;
import ru.cherry.springhomework.service.AuthorService;
import ru.cherry.springhomework.service.BookService;
import ru.cherry.springhomework.service.CommentService;
import ru.cherry.springhomework.service.GenreService;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    CommentService commentService;

    @Test
    void getAllBooks() throws Exception {
        Author author = new Author(1L, "Author-1");
        Genre genre = new Genre(1L, "Genre-1");
        Book book1 = new Book(1L, "Book-1", author, genre);
        Book book2 = new Book(1L, "Book-2", author, genre);
        List<Book> books = List.of(book1, book2);
        given(bookService.getAllBooks()).willReturn(books);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", equalTo(books)));
    }

    @Test
    void editBook() throws Exception {
        Author author = new Author(1L, "Author-1");
        Genre genre = new Genre(1L, "Genre-1");
        Book book = new Book(1L, "Book-1", author, genre);
        given(bookService.getById(1L)).willReturn(book);

        mockMvc.perform(get("/editbook?id=1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", equalTo(book)));
    }

    @Test
    void addNewBook() throws Exception {
        Author author = new Author(1L, "Author-1");
        Genre genre = new Genre(1L, "Genre-1");
        Book book = new Book(1L, "Book-1", author, genre);
        List<Author> authors = List.of(author);
        List<Genre> genres = List.of(genre);
        given(authorService.getAllAuthors()).willReturn(authors);
        given(genreService.getAllGenres()).willReturn(genres);

        mockMvc.perform(get("/addbook"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("authors", equalTo(authors)))
                .andExpect(model().attribute("genres", equalTo(genres)));
    }

    @Test
    void viewBook() throws Exception {
        Author author = new Author(1L, "Author-1");
        Genre genre = new Genre(1L, "Genre-1");
        Book book = new Book(1L, "Book-1", author, genre);
        Comment comment = new Comment(book, "Good");
        book.setComments(List.of(comment));
        given(bookService.getById(1L)).willReturn(book);

        mockMvc.perform(get("/viewbook?id=1"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", equalTo(book)));
    }

    @Test
    void deleteBook() throws Exception {
        Author author = new Author(1L, "Author-1");
        Genre genre = new Genre(1L, "Genre-1");
        Book book = new Book(1L, "Book-1", author, genre);
        given(bookService.getById(1L)).willReturn(book);
        given(bookService.deleteBook(1L)).willReturn(true);

        mockMvc.perform(get("/deletebook?id=1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void savebook() throws Exception {
        Author author = new Author(1L, "Author-1");
        Genre genre = new Genre(1L, "Genre-1");
        Book book = new Book(1L, "Book-1", author, genre);

        mockMvc.perform(post("/savebook")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("book", book))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(bookService, times(1)).save(book);
    }

    @Test
    void saveBook() throws Exception {
        Author author = new Author(1L, "Author-1");
        Genre genre = new Genre(1L, "Genre-1");
        Book book = new Book(1L, "Book-1", author, genre);
        given(bookService.getById(1L)).willReturn(book);

        mockMvc.perform(post("/savecbookchanges")
                .contentType(MediaType.APPLICATION_JSON)
                .flashAttr("book", book))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(bookService, times(1)).save(book);
    }

}