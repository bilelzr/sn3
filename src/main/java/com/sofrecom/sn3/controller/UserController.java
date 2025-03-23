package com.sofrecom.sn3.controller;


import com.sofrecom.sn3.entities.DTO.UserDto;
import com.sofrecom.sn3.security.AuthenticationService;
import com.sofrecom.sn3.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")

public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        authenticationService.signUp(userDto);
        return ResponseEntity.ok().body("User created successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }


    //@GetMapping("/byname/{taskName}")
    //public ResponseEntity<TaskDtoResponse> getTaskByName(@PathVariable String taskName){
    //  return ResponseEntity.ok().body(taskService.getTaskByName(taskName));
    //}
}
