package com.example.feedback.service;

import com.example.feedback.model.TeacherComment;
import com.example.feedback.repository.TeacherCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCommentService {
    @Autowired
    private TeacherCommentRepository teacherCommentRepository;

    public List<TeacherComment> findAll() {
        return teacherCommentRepository.findAll();
    }

    public TeacherComment findById(int id) {
        return teacherCommentRepository.findById(id).get();
    }

    public void save(TeacherComment comment) {
        teacherCommentRepository.save(comment);
    }

    public void delete(int id) {
        teacherCommentRepository.deleteById(id);
    }

    public List<TeacherComment> findAllByTeacherId(int id) {
        return teacherCommentRepository.findAllByTeacherId(id);
    }

    public TeacherComment findByUsernameAndTeacherId(String username, int id) {
        return teacherCommentRepository.findByUsernameAndTeacherId(username, id);
    }
}
