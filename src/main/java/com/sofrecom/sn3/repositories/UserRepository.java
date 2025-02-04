package com.sofrecom.sn3.repositories;

import com.sofrecom.sn3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select user from User user where user.email=:email")
    User findByEmail(@Param("email") String email);
}
