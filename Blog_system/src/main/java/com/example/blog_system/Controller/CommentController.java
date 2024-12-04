package com.example.blog_system.Controller;

import com.example.blog_system.ApiResponse.ApiResponse;
import com.example.blog_system.Model.Comment;
import com.example.blog_system.Service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllComment(){
        return ResponseEntity.status(200).body(commentService.getAllComment());
    }

    @PostMapping("/add")
    public ResponseEntity addComment(@RequestBody @Valid Comment comment, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@PathVariable Integer id,@RequestBody @Valid Comment comment,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        commentService.updateComment(id,comment);
        return ResponseEntity.status(200).body(new ApiResponse("Comment updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(200).body(new ApiResponse("Comment deleted"));
    }

    @GetMapping("/getCommentsOfUser/{id}")
    public ResponseEntity getCommentsOfUser(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.getCommentsOfUser(id));
    }

    @GetMapping("/getCommentOfPost/{id}")
    public ResponseEntity getCommentOfPost(@PathVariable Integer id){
        return ResponseEntity.status(200).body(commentService.getCommentOfPost(id));
    }
}
