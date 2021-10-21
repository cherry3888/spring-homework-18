package ru.cherry.springhomework.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.cherry.springhomework.dao.BookRepository;
import ru.cherry.springhomework.domain.Author;
import ru.cherry.springhomework.domain.Book;
import ru.cherry.springhomework.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookDaoTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private TestEntityManager em;


	@Test
	@DirtiesContext
	void getAllBooks() {
		List<Book> books = bookRepository.findAll();
		assertEquals(3, books.size());
	}

	@Test
	@DirtiesContext
	void insertBook() {
        Author author = em.find(Author.class, 1L);
        Genre genre = em.find(Genre.class, 1L);
		Book book = new Book("Book-4", author, genre);
		bookRepository.save(book);
		List<Book> books = bookRepository.findBooksByTitle(book.getTitle());
		assertEquals(books.size(), 1);
	}

	@Test
	@DirtiesContext
	void updateBook() {
		Book book = em.find(Book.class, 1L);
		String newTitle = "Book-new";
		book.setTitle(newTitle);
		bookRepository.save(book);
        Book book1 = em.find(Book.class, 1L);
        assertEquals(newTitle, book1.getTitle());
	}

	@Test
	@DirtiesContext
	void deleteBook() {
        Book book = em.find(Book.class, 1L);
		bookRepository.delete(book);
        Book book1 = em.find(Book.class, 1L);
		assertNull(book1);
	}

	@Test
	@DirtiesContext
	void getById() {
		Optional<Book> bookO = bookRepository.findById(2L);
		Book book = null;
		if (bookO.isPresent()) {
		    book = bookO.get();
        }
		assertNotNull(book);
	}

	@Test
	@DirtiesContext
	void getByTitle() {
		List<Book> books = bookRepository.findBooksByTitle("Book-2");
        assertEquals(books.size(), 1);
	}

}