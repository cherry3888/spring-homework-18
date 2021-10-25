package ru.cherry.springhomework.service;

import ru.cherry.springhomework.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Long bookId, String content);
    List<Comment> getByBookId(Long id);
}
