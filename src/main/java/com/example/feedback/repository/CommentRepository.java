package com.example.feedback.repository;

import com.example.feedback.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByUetClassId(int uetClassId);
    Comment findByUsernameAndUetClassId(String username, int id);
}
