package com.example.feedback.controller;

import com.example.feedback.model.Teacher;
import com.example.feedback.model.TeacherComment;
import com.example.feedback.service.TeacherCommentService;
import com.example.feedback.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class TeacherCommentController {

    @Autowired
    private TeacherCommentService teacherCommentService;

    @Autowired
    public TeacherService teacherService;

    @RequestMapping(value = "/teacherCommentsByTeacher/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherComment>> listAllCommentsByClass(@PathVariable("id") int id) {
        List<TeacherComment> accounts = teacherCommentService.findAllByUetTeacherId(id);
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<TeacherComment>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TeacherComment>>(accounts, HttpStatus.OK);
    }


    //API trả về List Comment.
    @RequestMapping(value = "/teacherComments", method = RequestMethod.GET)
    public ResponseEntity<List<TeacherComment>> listAllComments() {
        List<TeacherComment> accounts = teacherCommentService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<TeacherComment>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<TeacherComment>>(accounts, HttpStatus.OK);
    }

    //API trả về Comment có ID trên url.
    @RequestMapping(value = "/teacherComments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherComment> getCommentById(@PathVariable("id") int id) {
        System.out.println("Fetching TeacherComment with id " + id);
        TeacherComment account = teacherCommentService.findById(id);
        if (account == null) {
            System.out.println("TeacherComment with id " + id + " not found");
            return new ResponseEntity<TeacherComment>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TeacherComment>(account, HttpStatus.OK);
    }

    //API tạo một Admin mới.
    @RequestMapping(value = "/teacherComments", method = RequestMethod.POST)
    public ResponseEntity<Void> createComment(@RequestBody Map<String, String> map, UriComponentsBuilder ucBuilder) {
        int uetClassId = Integer.parseInt(map.get("teacherId"));
        String content = map.get("content");
        String username = map.get("username");
        String name = map.get("name");
        String avatarUrl = map.get("avatarUrl");
        int ratingValue = Integer.parseInt(map.get("ratingValue"));
        TeacherComment comment = teacherCommentService.findByUsernameAndTeacherId(username, uetClassId);
        if (comment == null) {
            comment = new TeacherComment(uetClassId, content, username, name, avatarUrl, ratingValue, new Date());
            Teacher teacher = teacherService.findById(uetClassId);
            double rateAverage = (teacher.getRateAverage() * teacher.getCountRate() + ratingValue) / (teacher.getCountRate() + 1);
            teacher.setCountRate(teacher.getCountRate() + 1);
            teacher.setRateAverage(rateAverage);
            teacherService.save(teacher);
            System.out.println("Creating Teacher Comment " + comment.getId());
        } else {
            comment.setLastUpdateTime(new Date());
            int lastRatingValue = comment.getRatingValue();
            comment.setRatingValue(ratingValue);
            comment.setContent(content);
            Teacher teacher = teacherService.findById(uetClassId);
            double rateAverage = (teacher.getRateAverage() * teacher.getCountRate() + ratingValue - lastRatingValue) / teacher.getCountRate();
            teacher.setRateAverage(rateAverage);
            teacherService.save(teacher);
            System.out.println("Updating Teacher Comment " + comment.getId());
        }
        teacherCommentService.save(comment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/teacherComments/{id}").buildAndExpand(comment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API cập nhật một Admin với ID trên url.
    @RequestMapping(value = "/teacherComments/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<TeacherComment> updateAdmin(@PathVariable("id") int id, @RequestBody TeacherComment comment) {
        System.out.println("Updating Comment " + id);

        TeacherComment curremComment = teacherCommentService.findById(id);

        if (curremComment == null) {
            System.out.println("Teacher Comment with id " + id + " not found");
            return new ResponseEntity<TeacherComment>(HttpStatus.NOT_FOUND);
        }

        curremComment = comment;

        teacherCommentService.save(curremComment);
        return new ResponseEntity<TeacherComment>(curremComment, HttpStatus.OK);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/teacherComments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TeacherComment> deleteComment(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Teacher Comment with id " + id);

        TeacherComment comment = teacherCommentService.findById(id);
        if (comment == null) {
            System.out.println("Unable to delete. Teacher Comment with id " + id + " not found");
            return new ResponseEntity<TeacherComment>(HttpStatus.NOT_FOUND);
        }

        teacherCommentService.delete(id);
        return new ResponseEntity<TeacherComment>(HttpStatus.NO_CONTENT);
    }
}
