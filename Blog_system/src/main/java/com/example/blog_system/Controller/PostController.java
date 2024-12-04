package com.example.blog_system.Controller;

import com.example.blog_system.ApiResponse.ApiResponse;
import com.example.blog_system.Model.Post;
import com.example.blog_system.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/get")
    public ResponseEntity getAllPost(){
        return ResponseEntity.status(200).body(postService.getAllPost());
    }

    @PostMapping("/add")
    public ResponseEntity addPost(@RequestBody @Valid Post post, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("Post added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updatePost(@PathVariable Integer id ,@RequestBody @Valid Post post,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        postService.updatePost(id,post);
        return ResponseEntity.status(200).body(new ApiResponse("Post updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePost(@PathVariable Integer id){
        postService.deletePost(id);
        return ResponseEntity.status(200).body(new ApiResponse("Post deleted"));
    }

    @GetMapping("/getUserPost/{id}")
    public ResponseEntity getAllPostByUserId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(postService.getAllPostByUserId(id));
    }

    @GetMapping("/findPostByCategoryId/{id}")
    public ResponseEntity findPostByCategoryId(@PathVariable Integer id){
        return ResponseEntity.status(200).body(postService.findPostByCategoryId(id));
    }

    @GetMapping("/findAllByTitle/{title}")
    public ResponseEntity findAllByTitle(@PathVariable String title){
        return ResponseEntity.status(200).body(postService.findAllByTitle(title));
    }

}
