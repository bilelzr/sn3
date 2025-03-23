package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.DTO.UserDto;
import com.sofrecom.sn3.entities.User;
import com.sofrecom.sn3.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return username -> userRepository.findByEmail(username);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return DtoConverter.convertListUserToDto(users);
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return DtoConverter.convertUserToDto(user);
    }

    @Override
    public boolean deleteUser(User user) {
        User userToDelete = userRepository.findByEmail(user.getEmail());
        if (userToDelete != null) {
            userRepository.delete(userToDelete);
            return true;
        }
        return false;
    }

    @Override
    public boolean lockUserAccount(String email) {
        User userToLock = userRepository.findByEmail(email);
        if (userToLock != null) {
            userToLock.setAccountNonLocked(false);
            userRepository.save(userToLock);
            return true;
        }
        return false;
    }
}
