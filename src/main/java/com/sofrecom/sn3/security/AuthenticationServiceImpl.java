package com.sofrecom.sn3.security;

import com.sofrecom.sn3.entities.DTO.AuthenticationDto;
import com.sofrecom.sn3.entities.DTO.SignInRequest;
import com.sofrecom.sn3.entities.DTO.UserDto;
import com.sofrecom.sn3.entities.Role;
import com.sofrecom.sn3.entities.User;
import com.sofrecom.sn3.exceptions.UserAlreadyExistsException;
import com.sofrecom.sn3.exceptions.UserNotFoundException;
import com.sofrecom.sn3.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServices jwtServices;
    private final AuthenticationManager authenticationManager;
    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtServices jwtServices, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtServices = jwtServices;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationDto signUp(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
            throw new UserAlreadyExistsException("Email already used!");
        }
        User userToPersist = new User();
        userToPersist.setEmail(userDto.getEmail());
        userToPersist.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userToPersist.setFirstName(userDto.getFirstName());
        userToPersist.setLastName(userDto.getLastName());
        userToPersist.setPhone(userDto.getPhone());
        userToPersist.setRole(Role.ADMIN);

        userRepository.save(userToPersist);
        String token = jwtServices.generateToken(userToPersist);
        AuthenticationDto authenticationDto = new AuthenticationDto();
        authenticationDto.setToken(token);

        return authenticationDto;
    }

    @Override
    public AuthenticationDto signIn(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String email= authentication.getName();
        User user = userRepository.findByEmail(email);
        if (user ==null) {
            throw new UserNotFoundException("User not found!");
        }
        String token = jwtServices.generateToken(user);
        AuthenticationDto authenticationDto = new AuthenticationDto();
        authenticationDto.setToken(token);

        return authenticationDto;
    }
}
