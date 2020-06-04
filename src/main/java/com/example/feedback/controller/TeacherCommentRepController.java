package com.example.feedback.controller;

import com.example.feedback.model.TeacherCommentRep;
import com.example.feedback.service.TeacherCommentRepService;
import com.example.feedback.service.TeacherCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class TeacherCommentRepController {

    @Autowired
    private TeacherCommentRepService teacherCommentService;

    @RequestMapping(value = "/teacherCommentRepsByCommentId/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherCommentRep>> listAllCommentsByClass(@PathVariable("id") int id) {
        List<TeacherCommentRep> accounts = teacherCommentService.findAllByCommentId(id);
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<TeacherCommentRep>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TeacherCommentRep>>(accounts, HttpStatus.OK);
    }


    //API trả về List Comment.
    @RequestMapping(value = "/teacherCommentReps", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherCommentRep>> listAllComments() {
        List<TeacherCommentRep> accounts = teacherCommentService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<TeacherCommentRep>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TeacherCommentRep>>(accounts, HttpStatus.OK);
    }

    //API trả về Comment có ID trên url.
    @RequestMapping(value = "/teacherCommentReps/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherCommentRep> getCommentById(@PathVariable("id") int id) {
        System.out.println("Fetching Teacher Comment Rep with id " + id);
        TeacherCommentRep account = teacherCommentService.findById(id);
        if (account == null) {
            System.out.println("Teacher Comment Rep with id " + id + " not found");
            return new ResponseEntity<TeacherCommentRep>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TeacherCommentRep>(account, HttpStatus.OK);
    }

    //API tạo một Admin mới.
    @RequestMapping(value = "/teacherCommentReps", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody TeacherCommentRep commentRep, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Teacher Comment Rep " + commentRep.getId());
        teacherCommentService.save(commentRep);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/teacherCommentReps/{id}").buildAndExpand(commentRep.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API cập nhật một Admin với ID trên url.
    @RequestMapping(value = "/teacherCommentReps/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<TeacherCommentRep> updateAdmin(@PathVariable("id") int id, @RequestBody TeacherCommentRep comment) {
        System.out.println("Updating Teacher Comment Rep " + id);

        TeacherCommentRep curremComment = teacherCommentService.findById(id);

        if (curremComment == null) {
            System.out.println("Teacher Comment Rep with id " + id + " not found");
            return new ResponseEntity<TeacherCommentRep>(HttpStatus.NOT_FOUND);
        }

        curremComment = comment;

        teacherCommentService.save(curremComment);
        return new ResponseEntity<TeacherCommentRep>(curremComment, HttpStatus.OK);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/teacherCommentReps/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TeacherCommentRep> deleteComment(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Teacher Comment Rep with id " + id);

        TeacherCommentRep comment = teacherCommentService.findById(id);
        if (comment == null) {
            System.out.println("Unable to delete. Teacher Comment Rep with id " + id + " not found");
            return new ResponseEntity<TeacherCommentRep>(HttpStatus.NOT_FOUND);
        }

        teacherCommentService.delete(id);
        return new ResponseEntity<TeacherCommentRep>(HttpStatus.NO_CONTENT);
    }
}
