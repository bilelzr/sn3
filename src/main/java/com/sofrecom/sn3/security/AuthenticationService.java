package com.sofrecom.sn3.security;

import com.sofrecom.sn3.entities.DTO.auth.AuthenticationDto;
import com.sofrecom.sn3.entities.DTO.auth.SignInRequest;
import com.sofrecom.sn3.entities.DTO.user.UserDto;

public interface AuthenticationService {

    AuthenticationDto signUp(UserDto userDto);

    AuthenticationDto signIn(SignInRequest signInRequest);
    // UserDto createNewUser(UserDto userDto);

}
