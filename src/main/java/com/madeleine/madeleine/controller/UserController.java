package com.madeleine.madeleine.controller;

import javax.validation.Valid;

import com.madeleine.madeleine.DTO.UserDTO.UserRequest;
import com.madeleine.madeleine.DTO.UserDTO.UserResponse;
import com.madeleine.madeleine.model.CustomUserDetails;
import com.madeleine.madeleine.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;



@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userDTO){
        log.info(userDTO.getUsername());
        CustomUserDetails userDetails =  userService.createUser(userDTO); 
        return ResponseEntity.ok().body(new UserResponse("OK", "유저 생성 완료", userDetails));
    }

    @RequestMapping(value="/{userId}", method=RequestMethod.GET)
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long userId) {
        CustomUserDetails userDetails = userService.findById(userId);
        return ResponseEntity.ok().body(new UserResponse("OK", "유저", userDetails));
    }

    @RequestMapping(value="/subscribe/{newsletterId}/", method=RequestMethod.POST)
    public ResponseEntity<UserResponse> subscribeNewsletter(@PathVariable Long newsletterId) {
        
        return new SomeData();
    }
    
}
