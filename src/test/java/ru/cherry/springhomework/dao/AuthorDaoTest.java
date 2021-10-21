package ru.cherry.springhomework.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.cherry.springhomework.dao.AuthorRepository;
import ru.cherry.springhomework.domain.Author;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class AuthorDaoTest {

	@Autowired
	private AuthorRepository authorRepository;
    @Autowired
    private TestEntityManager em;

	@Test
	@DirtiesContext
	void getAllAuthors() {
		List<Author> authors = authorRepository.findAll();
		assertEquals(3, authors.size());
	}

	@Test
	@DirtiesContext
	void insertAuthor() {
		Author author = new Author(4L,"Author-4");
		authorRepository.save(author);
		Author author1 = em.find(Author.class, 4L);
		assertNotNull(author1);
	}

	@Test
	@DirtiesContext
	void updateAuthor() {
		Optional<Author> authorO = authorRepository.findById(3L);
		Author author = null;
		if (authorO.isPresent()) {
			author = authorO.get();
			author.setName("Author-123");
			authorRepository.save(author);
		}
		Author author1 = em.find(Author.class, 3L);
		assertNotNull(author);
		assertEquals(author1.getName(), author.getName());
	}

	@Test
	@DirtiesContext
	void deleteAuthor() {
		Author author = em.find(Author.class, 1L);
		authorRepository.delete(author);
		Author author1 = em.find(Author.class, 1L);
		assertNull(author1);
	}

	@Test
	@DirtiesContext
	void getById() {
		Optional<Author> authorO = authorRepository.findById(2L);
		Author author = null;
		if (authorO.isPresent()) {
			author = authorO.get();
		}
		assertNotNull(author);
	}

	@Test
	@DirtiesContext
	void getByName() {
		Optional<Author> author = authorRepository.findByName("Author-1");
		assertFalse(author.isEmpty());
	}

}