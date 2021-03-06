package com.example.feedback.service;

import com.example.feedback.model.Comment;
import com.example.feedback.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(int id) {
        return commentRepository.findById(id).get();
    }

    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    public void delete(int id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> findAllByUetClassId(int id) {
        return commentRepository.findAllByUetClassId(id);
    }

    public Comment findByUsernameAndClass(String username, int id) {
        return commentRepository.findByUsernameAndUetClassId(username, id);
    }
}
