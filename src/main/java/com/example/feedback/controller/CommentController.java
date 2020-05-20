package com.example.feedback.controller;

import com.example.feedback.model.Comment;
import com.example.feedback.service.CommentService;
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
public class CommentController {

    @Autowired
    private CommentService commentService;

    //API trả về List Comment.
    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> listAllComments() {
        List<Comment> accounts = commentService.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Comment>>(accounts, HttpStatus.OK);
    }

    //API trả về Comment có ID trên url.
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> getCommentById(@PathVariable("id") int id) {
        System.out.println("Fetching Comment with id " + id);
        Comment account = commentService.findById(id);
        if (account == null) {
            System.out.println("Comment with id " + id + " not found");
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Comment>(account, HttpStatus.OK);
    }

    //API tạo một Admin mới.
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public ResponseEntity<Void> createComment(@RequestBody Comment comment, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Comment " + comment.getId());
        commentService.save(comment);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/comments/{id}").buildAndExpand(comment.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //API cập nhật một Admin với ID trên url.
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Comment> updateAdmin(@PathVariable("id") int id, @RequestBody Comment comment) {
        System.out.println("Updating Comment " + id);

        Comment curremComment = commentService.findById(id);

        if (curremComment == null) {
            System.out.println("Comment with id " + id + " not found");
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }

        curremComment = comment;

        commentService.save(curremComment);
        return new ResponseEntity<Comment>(curremComment, HttpStatus.OK);
    }

    //API xóa một Admin với ID trên url.
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Comment with id " + id);

        Comment comment = commentService.findById(id);
        if (comment == null) {
            System.out.println("Unable to delete. Comment with id " + id + " not found");
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }

        commentService.delete(id);
        return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
    }
}
