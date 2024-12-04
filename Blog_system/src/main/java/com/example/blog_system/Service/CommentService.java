package com.example.blog_system.Service;

import com.example.blog_system.ApiResponse.ApiException;
import com.example.blog_system.Model.Category;
import com.example.blog_system.Model.Comment;
import com.example.blog_system.Model.Post;
import com.example.blog_system.Model.User;
import com.example.blog_system.Repository.CommentRepository;
import com.example.blog_system.Repository.PostRepository;
import com.example.blog_system.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<Comment> getAllComment(){
        return commentRepository.findAll();
    }

    public void addComment(Comment comment){
        User user =userRepository.findUserById(comment.getUserId());
        Post post =postRepository.findPostById(comment.getPostId());

        if(user==null){
            throw new ApiException("User not found");
        }
        if(post==null){
            throw new ApiException("post not found");
        }
        comment.setCommentDate(LocalDateTime.now());
        commentRepository.save(comment);
    }


    public void updateComment(Integer id ,Comment comment){
        User user =userRepository.findUserById(comment.getUserId());
        Post post =postRepository.findPostById(comment.getPostId());

        if(user==null){
            throw new ApiException("User not found");
        }
        if(post==null){
            throw new ApiException("post not found");
        }
        Comment oldComment =commentRepository.findCommentById(id);
        if(oldComment==null){
            throw new ApiException("Comment not found");
        }

        oldComment.setCommentDate(comment.getCommentDate());
        oldComment.setContent(comment.getContent());
        oldComment.setPostId(comment.getPostId());
        oldComment.setUserId(comment.getUserId());

        commentRepository.save(oldComment);

    }

    public void deleteComment(Integer id){
        Comment comment=commentRepository.findCommentById(id);
        if(comment==null){
            throw new ApiException("Comment not found");
        }

        commentRepository.delete(comment);

    }


    public List<Comment> getCommentsOfUser(Integer id){
        List<Comment> commentList =commentRepository.getCommentsOfUser(id);
        if(commentList.isEmpty()){
            throw new ApiException("User comments not found");
        }
        return commentList;
    }

    public List<Comment> getCommentOfPost(Integer id){
        List<Comment> commentList =commentRepository.getCommentOfPost(id);
        if(commentList.isEmpty()){
            throw new ApiException("comment not found");
        }
        return commentList;
    }

}
