package com.sofrecom.sn3.repositories;

import com.sofrecom.sn3.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select user from User user where user.email=:email")
    User findByEmail(@Param("email") String email);

    @Query("select user from User user where user.uuid in :uuid")
    List<User> findByUuids(List<UUID> uuids);
}
