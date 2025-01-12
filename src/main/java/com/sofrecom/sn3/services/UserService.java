package com.sofrecom.sn3.services;
import com.sofrecom.sn3.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    UserDetailsService getUserDetailsService();
    List<User> findAllUsers();
    User findUserByEmail(String email);
    boolean deleteUser(User user);
    boolean lockUserAccount(String email);
}
