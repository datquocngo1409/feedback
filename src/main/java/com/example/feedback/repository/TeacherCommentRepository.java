package com.example.feedback.repository;

import com.example.feedback.model.TeacherComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherCommentRepository extends JpaRepository<TeacherComment, Integer> {
    List<TeacherComment> findAllByTeacherId(int uetClassId);
    TeacherComment findByUsernameAndTeacherId(String username, int id);
}
