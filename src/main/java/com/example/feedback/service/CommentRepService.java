package com.example.feedback.service;

import com.example.feedback.model.CommentRep;
import com.example.feedback.repository.CommentRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentRepService {
    @Autowired
    private CommentRepRepository commentRepRepository;

    public List<CommentRep> findAll() {
        return commentRepRepository.findAll();
    }

    public CommentRep findById(int id) {
        return commentRepRepository.findById(id).get();
    }

    public void save(CommentRep comment) {
        commentRepRepository.save(comment);
    }

    public void delete(int id) {
        commentRepRepository.deleteById(id);
    }

    public List<CommentRep> findAllByCommentId(int id) {
        return commentRepRepository.findAllByCommentId(id);
    }
}
