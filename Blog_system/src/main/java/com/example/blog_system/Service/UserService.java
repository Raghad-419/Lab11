package com.example.blog_system.Service;

import com.example.blog_system.ApiResponse.ApiException;
import com.example.blog_system.Model.User;
import com.example.blog_system.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        user.setRegistrationDate(LocalDateTime.now());
        userRepository.save(user);
    }

    public void updateUser(Integer id,User user){
        User oldUser =userRepository.findUserById(id);
        if(oldUser==null){
            throw new ApiException("User not found");
        }
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setUsername(user.getUsername());
        oldUser.setRegistrationDate(user.getRegistrationDate());
        userRepository.save(oldUser);
    }


    public void deleteUser(Integer id){
        User user =userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }


    public User findUserByEmail(String email){
        User user =userRepository.findUserByEmail(email);
        if(user==null){
            throw new ApiException("User not found");
        }
        return user;
    }
}
