package com.example.feedback.service;

import com.example.feedback.model.UetClass;
import com.example.feedback.repository.UetClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UetClassService {
    @Autowired
    private UetClassRepository UetClassRepository;

    public List<UetClass> findAll() {
        return UetClassRepository.findAll();
    }

    public UetClass findById(int id) {
        return UetClassRepository.findById(id).get();
    }

    public void save(UetClass uetClass) {
        UetClassRepository.save(uetClass);
    }

    public void delete(int id) {
        UetClassRepository.deleteById(id);
    }
}
