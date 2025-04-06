package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.DTO.DtoConverter;
import com.sofrecom.sn3.entities.DTO.user.UserDto;
import com.sofrecom.sn3.entities.User;
import com.sofrecom.sn3.entities.enumeration.Role;
import com.sofrecom.sn3.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
    public List<UserDto> findAllNonAdminUsers() {
        List<User> users = userRepository.findAll().stream().filter(user -> user.getRole() != Role.ADMIN).toList();
        return DtoConverter.convertListUserToDto(users);

    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return DtoConverter.convertUserToDto(user);
    }

    @Override
    public boolean deleteUser(UUID uuid) {
        User userToDelete = userRepository.findByUuid(uuid);
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
