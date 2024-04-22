package dev.wandessonsoares.services;

import dev.wandessonsoares.domain.user.User;
import dev.wandessonsoares.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id){
        return userRepository.findById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public Optional<User> updateUser(User updateUser, Long id){
        Optional<User> userSaved = userRepository.findById(id);
        if (userSaved.isPresent()){
            User user = userSaved.get();
            user.setFirstName(updateUser.getFirstName());
            user.setEmail(updateUser.getEmail());

            userRepository.save(user);
        }
        return userSaved;
    }

    public void deleteUserById(Long id){
        if (userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
    }

}
