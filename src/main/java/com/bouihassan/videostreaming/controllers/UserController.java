package com.bouihassan.videostreaming.controllers;

import com.bouihassan.videostreaming.constants.Paths;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Paths.USER_ROOT)
public class UserController {
    @GetMapping
    public String hello(HttpServletRequest request){
        return "Hello World "+request.getSession().getId();
    }

}
