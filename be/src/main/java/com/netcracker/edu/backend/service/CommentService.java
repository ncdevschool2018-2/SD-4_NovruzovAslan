package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.Comment;

import java.util.Optional;

public interface CommentService {
    Comment saveComment(Comment category);
    Optional<Comment> getCommentById(Long id);
    Iterable<Comment> getAllComments();
    void deleteComment(Long id);
}
