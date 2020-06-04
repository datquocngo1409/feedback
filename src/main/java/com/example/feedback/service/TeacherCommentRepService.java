package com.example.feedback.service;

import com.example.feedback.model.TeacherCommentRep;
import com.example.feedback.repository.TeacherCommentRepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherCommentRepService {
    @Autowired
    private TeacherCommentRepRepository teacherCommentRepRepository;

    public List<TeacherCommentRep> findAll() {
        return teacherCommentRepRepository.findAll();
    }

    public TeacherCommentRep findById(int id) {
        return teacherCommentRepRepository.findById(id).get();
    }

    public void save(TeacherCommentRep comment) {
        teacherCommentRepRepository.save(comment);
    }

    public void delete(int id) {
        teacherCommentRepRepository.deleteById(id);
    }

    public List<TeacherCommentRep> findAllByCommentId(int id) {
        return teacherCommentRepRepository.findAllByTeacherCommentId(id);
    }
}
