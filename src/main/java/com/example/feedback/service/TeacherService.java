package com.example.feedback.service;

import com.example.feedback.model.Teacher;
import com.example.feedback.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public Teacher findById(int id) {
        return teacherRepository.findById(id).get();
    }

    public void save(Teacher user) {
        teacherRepository.save(user);
    }

    public void delete(int id) {
        teacherRepository.deleteById(id);
    }

    public List<Teacher> findByName(String name) {
        return teacherRepository.findAllByName(name);
    }
}
