package com.sofrecom.sn3.controller;

import com.sofrecom.sn3.entities.DTO.auth.SignInRequest;
import com.sofrecom.sn3.entities.DTO.user.UserDto;
import com.sofrecom.sn3.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.signUp(userDto));
    }

    @GetMapping
    public String test() {
        return "test";
    }
}
