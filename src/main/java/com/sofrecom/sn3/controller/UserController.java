package com.sofrecom.sn3.controller;


import com.sofrecom.sn3.entities.DTO.ApiResponse;
import com.sofrecom.sn3.entities.DTO.user.UserDto;
import com.sofrecom.sn3.security.AuthenticationService;
import com.sofrecom.sn3.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
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
        return ResponseEntity.ok(new ApiResponse("User created successfully"));    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok().body(userService.findAllUsers());
    }


    @GetMapping("/all/nonadmin")
    public ResponseEntity<List<UserDto>> getAllNonAdminUsers() {
        return ResponseEntity.ok().body(userService.findAllNonAdminUsers());
    }



    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok().body(userService.findUserByEmail(email));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity deleteUser(@PathVariable("uuid") String uuid) {
        userService.deleteUser(UUID.fromString(uuid));
        return ResponseEntity.ok(new ApiResponse("User deleted successfully"));
    }

}
