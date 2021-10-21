package ru.cherry.springhomework.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.cherry.springhomework.domain.Book;
import ru.cherry.springhomework.service.AuthorService;
import ru.cherry.springhomework.service.BookService;
import ru.cherry.springhomework.service.CommentService;
import ru.cherry.springhomework.service.GenreService;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    public BookController(BookService bookService, AuthorService authorService, GenreService genreService, CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/editbook")
    public String editBook(@RequestParam("id") Long id, Model model) {
        Book book = bookService.getById(id);
        if (null != book) {
            model.addAttribute("book", book);
            return "edit_book";
        }
        return "redirect:/";
    }

    @GetMapping("/viewbook")
    public String viewBook(@RequestParam("id") Long id, Model model) {
        Book book = bookService.getById(id);
        if (null != book) {
            model.addAttribute("book", book);
            return "view_book";
        }
        return "redirect:/";
    }

    @GetMapping("/deletebook")
    public String deleteBook(@RequestParam("id") Long id) {
        Book book = bookService.getById(id);
        if (null != book) {
            bookService.deleteBook(id);
        }
        return "redirect:/";
    }

    @GetMapping("/addbook")
    public String addNewBook(Book book, Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "add_book";
    }

    @PostMapping("/savecbookchanges")
    public String saveBook(Book book) {
        Book exBook = bookService.getById(book.getId());
        exBook.setTitle(book.getTitle());
        bookService.save(exBook);
        return "redirect:/";
    }

    @PostMapping("/savebook")
    public String saveNewBook(Book book) {
        bookService.save(book);
        return "redirect:/";
    }

}
