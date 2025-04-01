package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.DTO.UserDto;
import com.sofrecom.sn3.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDetailsService getUserDetailsService();

    List<UserDto> findAllUsers();


    List<UserDto> findAllNonAdminUsers();
    UserDto findUserByEmail(String email);

    boolean deleteUser(UUID uuid);

    boolean lockUserAccount(String email);
}
