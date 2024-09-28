package com.bouihassan.videostreaming.controllers;

import com.bouihassan.videostreaming.constants.Paths;
import com.bouihassan.videostreaming.models.User;
import com.bouihassan.videostreaming.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.USER_ROOT)
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return  ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id){
        return null;
    }
    @DeleteMapping("/{id}")
    public Void deleteUser(@PathVariable String id) {
       userService.deleteUser(id);
       return null;
    }

}
