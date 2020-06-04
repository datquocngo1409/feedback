package com.example.feedback.repository;

import com.example.feedback.model.CommentRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepRepository extends JpaRepository<CommentRep, Integer> {
    List<CommentRep> findAllByCommentId(int commentId);
}
