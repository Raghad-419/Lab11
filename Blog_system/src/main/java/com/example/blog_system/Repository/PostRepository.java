package com.example.blog_system.Repository;

import com.example.blog_system.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
    Post findPostById(Integer id);

    //Method th get all user posts by user id
    @Query("select p from Post p where p.userId=?1")
    List<Post> getAllPostByUserId(Integer id);


    //method to get all post by category id
    List<Post> findPostByCategoryId(Integer id);


//method to get post by title
    List<Post> findAllByTitle(String title);

}
