package dev.wandessonsoares.controller;

import dev.wandessonsoares.domain.user.User;
import dev.wandessonsoares.services.UserService;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return userService.findUserById(id)
                .map(record -> {
                    return ResponseEntity.status(HttpStatus.OK).body(record);
                }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        return userService.findUserById(id)
                .map(record -> {
                    userService.deleteUserById(id);
                    return ResponseEntity.status(HttpStatus.OK).build();
                }).orElse(
                        ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody User updateUser) {
        return userService.findUserById(id)
            .map(record -> {
                userService.updateUser(updateUser, record.getId());
                return ResponseEntity.status(HttpStatus.OK).build();
            }).orElse(
                ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            );
    }
}
