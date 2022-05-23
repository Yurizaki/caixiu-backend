package com.caixiu.backend.user.controller;

import com.caixiu.backend.user.model.User;
import com.caixiu.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class UserController {
//
//    @Autowired
//    UserRepository userRepository;
//
//
//    @RequestMapping(value = "/users")
//    public User getUser() {
//        return userRepository.findUserByUsername("alex");
//    }
//}
