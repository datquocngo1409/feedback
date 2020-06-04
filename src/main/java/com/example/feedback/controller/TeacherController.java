package com.example.feedback.controller;

import com.example.feedback.model.Teacher;
import com.example.feedback.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class TeacherController {
    @Autowired
    public TeacherService teacherService;

    @RequestMapping(value = "/teacherByName", method = RequestMethod.POST)
    public ResponseEntity<Teacher> getByName(@RequestBody Map<String, String> map) {
        String name = map.get("name");
        Teacher teacher = teacherService.findByName(name).get(0);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
    }


    //API trả về List Teacher.
    @RequestMapping(value = "/teachers", method = RequestMethod.GET)
    public ResponseEntity<List<Teacher>> listAllTeachers() {
        List<Teacher> accounts = teacherService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<Teacher>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Teacher>>(accounts, HttpStatus.OK);
    }

    //API trả về Teacher có ID trên url.
    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("id") int id) {
        System.out.println("Fetching Teacher with id " + id);
        Teacher account = teacherService.findById(id);
        if (account == null) {
            System.out.println("Teacher with id " + id + " not found");
            return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Teacher>(account, HttpStatus.OK);
    }

    //API tạo một Admin mới.
    @RequestMapping(value = "/teachers", method = RequestMethod.POST)
    public ResponseEntity<Void> createTeacher(@RequestBody Teacher teacher, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Teacher " + teacher.getName());
        teacherService.save(teacher);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/teachers/{id}").buildAndExpand(teacher.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API cập nhật một Admin với ID trên url.
    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Teacher> updateAdmin(@PathVariable("id") int id, @RequestBody Teacher teacher) {
        System.out.println("Updating Teacher " + id);

        Teacher curremTeacher = teacherService.findById(id);

        if (curremTeacher == null) {
            System.out.println("Teacher with id " + id + " not found");
            return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
        }

        curremTeacher = teacher;

        teacherService.save(curremTeacher);
        return new ResponseEntity<Teacher>(curremTeacher, HttpStatus.OK);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/teachers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Teacher> deleteTeacher(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Teacher with id " + id);

        Teacher teacher = teacherService.findById(id);
        if (teacher == null) {
            System.out.println("Unable to delete. Teacher with id " + id + " not found");
            return new ResponseEntity<Teacher>(HttpStatus.NOT_FOUND);
        }
        teacherService.delete(id);
        return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
    }
}
