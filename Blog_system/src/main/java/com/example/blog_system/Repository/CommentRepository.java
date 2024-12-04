package com.example.blog_system.Repository;

import com.example.blog_system.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Comment findCommentById(Integer id);

    //method to get user comments by id
    @Query("select c from Comment c where c.userId=?1")
    List<Comment> getCommentsOfUser(Integer id);


    //method to get all comment under specific post by post id
    @Query("select c from Comment c where c.postId=?1")
    List<Comment> getCommentOfPost(Integer id);
}
