package com.example.feedback.controller;

import com.example.feedback.model.CommentRep;
import com.example.feedback.service.CommentRepService;
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
public class CommentRepController {

    @Autowired
    private CommentRepService commentRepService;

    @RequestMapping(value = "/commentRepsByCommentId/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<CommentRep>> listAllCommentsByClass(@PathVariable("id") int id) {
        List<CommentRep> accounts = commentRepService.findAllByCommentId(id);
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<CommentRep>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<CommentRep>>(accounts, HttpStatus.OK);
    }


    //API trả về List Comment.
    @RequestMapping(value = "/commentReps", method = RequestMethod.GET)
    public ResponseEntity<List<CommentRep>> listAllComments() {
        List<CommentRep> accounts = commentRepService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<CommentRep>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<CommentRep>>(accounts, HttpStatus.OK);
    }

    //API trả về Comment có ID trên url.
    @RequestMapping(value = "/commentReps/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommentRep> getCommentById(@PathVariable("id") int id) {
        System.out.println("Fetching Comment with id " + id);
        CommentRep account = commentRepService.findById(id);
        if (account == null) {
            System.out.println("Comment with id " + id + " not found");
            return new ResponseEntity<CommentRep>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CommentRep>(account, HttpStatus.OK);
    }

    //API tạo một Admin mới.
    @RequestMapping(value = "/commentReps", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody CommentRep commentRep, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Comment " + commentRep.getId());
        commentRepService.save(commentRep);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/commentReps/{id}").buildAndExpand(commentRep.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API cập nhật một Admin với ID trên url.
    @RequestMapping(value = "/commentReps/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<CommentRep> updateAdmin(@PathVariable("id") int id, @RequestBody CommentRep comment) {
        System.out.println("Updating Comment " + id);

        CommentRep curremComment = commentRepService.findById(id);

        if (curremComment == null) {
            System.out.println("Comment with id " + id + " not found");
            return new ResponseEntity<CommentRep>(HttpStatus.NOT_FOUND);
        }

        curremComment = comment;

        commentRepService.save(curremComment);
        return new ResponseEntity<CommentRep>(curremComment, HttpStatus.OK);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/commentReps/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CommentRep> deleteComment(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Comment with id " + id);

        CommentRep comment = commentRepService.findById(id);
        if (comment == null) {
            System.out.println("Unable to delete. Comment with id " + id + " not found");
            return new ResponseEntity<CommentRep>(HttpStatus.NOT_FOUND);
        }

        commentRepService.delete(id);
        return new ResponseEntity<CommentRep>(HttpStatus.NO_CONTENT);
    }
}
