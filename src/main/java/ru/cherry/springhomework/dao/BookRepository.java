package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.domain.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByTitle(String title);
    Optional<Book> findBookByTitleAndAuthor_IdAndGenre_Id(String title, Long authorId, Long genreId);

}
