package ru.cherry.springhomework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cherry.springhomework.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
