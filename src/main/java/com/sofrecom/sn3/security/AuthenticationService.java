package com.sofrecom.sn3.security;

import com.sofrecom.sn3.entities.DTO.AuthenticationDto;
import com.sofrecom.sn3.entities.DTO.SignInRequest;
import com.sofrecom.sn3.entities.DTO.UserDto;

public interface AuthenticationService {

    AuthenticationDto signUp(UserDto userDto);

    AuthenticationDto signIn(SignInRequest signInRequest);
    // UserDto createNewUser(UserDto userDto);

}
