package com.example.feedback.repository;

import com.example.feedback.model.TeacherCommentRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCommentRepRepository extends JpaRepository<TeacherCommentRep, Integer> {
    List<TeacherCommentRep> findAllByTeacherCommentId(int teacherCommentId);
}
