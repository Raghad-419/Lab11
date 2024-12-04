package com.example.blog_system.Service;

import com.example.blog_system.ApiResponse.ApiException;
import com.example.blog_system.Model.Category;
import com.example.blog_system.Model.Post;
import com.example.blog_system.Model.User;
import com.example.blog_system.Repository.CategoryRepository;
import com.example.blog_system.Repository.PostRepository;
import com.example.blog_system.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

    public void addPost(Post post){
        User user =userRepository.findUserById(post.getUserId());
        Category category=categoryRepository.findCategoriesById(post.getCategoryId());

        if(user==null){
            throw new ApiException("User not found");
        }
        if(category==null){
            throw new ApiException("Category not found");
        }
        post.setPublishDate(LocalDateTime.now());
        postRepository.save(post);
    }

    public void updatePost(Integer id,Post post){
        User user =userRepository.findUserById(post.getUserId());
        Category category=categoryRepository.findCategoriesById(post.getCategoryId());

        if(user==null){
            throw new ApiException("User not found");
        }
        if(category==null){
            throw new ApiException("Category not found");
        }
        Post oldPost =postRepository.findPostById(id);
        if(oldPost==null){
            throw new ApiException("Post not found");
        }
        oldPost.setCategoryId(post.getCategoryId());
        oldPost.setContent(post.getContent());
        oldPost.setTitle(post.getTitle());
        oldPost.setUserId(post.getUserId());
        oldPost.setPublishDate(post.getPublishDate());
        postRepository.save(oldPost);

    }

    public void deletePost(Integer id){
        Post post=postRepository.findPostById(id);
        if(post==null){
            throw new ApiException("Post not found");
        }
        postRepository.delete(post);
    }


    public List<Post> getAllPostByUserId(Integer id){
        List<Post> postList =postRepository.getAllPostByUserId(id);
        if(postList.isEmpty()){
            throw new ApiException("User posts not found");
        }
        return postList;
    }

    public List<Post> findPostByCategoryId(Integer id){
        List<Post> postList=postRepository.findPostByCategoryId(id);
        if(postList.isEmpty()){
            throw new ApiException("category posts not found");
        }
        return postList;
    }

    public List<Post> findAllByTitle(String title){
        List<Post> postList=postRepository.findAllByTitle(title);
        if(postList.isEmpty()){
            throw new ApiException("posts not found");
        }
        return postList;
    }

}
