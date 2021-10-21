package ru.cherry.springhomework.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.cherry.springhomework.dao.GenreRepository;
import ru.cherry.springhomework.domain.Genre;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class GenreDaoTest {

	@Autowired
	private GenreRepository genreRepository;
    @Autowired
    private TestEntityManager em;

	@Test
	@DirtiesContext
	void getAllGenres() {
		List<Genre> genres = genreRepository.findAll();
		assertEquals(3, genres.size());
	}

	@Test
	@DirtiesContext
	void insertGenre() {
		Genre genre = new Genre(4L,"Genre-4");
		genreRepository.save(genre);
		Genre genre1 = em.find(Genre.class, 4L);
		assertNotNull(genre1);
	}

	@Test
	@DirtiesContext
	void updateGenre() {
		Optional<Genre> genreO = genreRepository.findById(3L);
		Genre genre = null;
		if (genreO.isPresent()) {
			genre = genreO.get();
			genre.setName("Genre-123");
			genreRepository.save(genre);
		}
		Genre genre1 = em.find(Genre.class, 3L);
		assertNotNull(genre);
		assertEquals(genre1.getName(), genre.getName());
	}

	@Test
	@DirtiesContext
	void deleteGenre() {
		Genre genre = em.find(Genre.class, 1L);
		genreRepository.delete(genre);
		Genre genre1 = em.find(Genre.class, 1L);
		assertNull(genre1);
	}

	@Test
	@DirtiesContext
	void getById() {
		Optional<Genre> genreO = genreRepository.findById(2L);
		Genre genre = null;
		if (genreO.isPresent()) {
			genre = genreO.get();
		}
		assertNotNull(genre);
	}

	@Test
	@DirtiesContext
	void getByName() {
		Optional<Genre> genre = genreRepository.findByName("Genre-1");
		assertFalse(genre.isEmpty());
	}

}