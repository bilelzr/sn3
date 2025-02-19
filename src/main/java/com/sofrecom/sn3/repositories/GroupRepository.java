package com.sofrecom.sn3.repositories;

import com.sofrecom.sn3.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select group from Group group where group.groupName ilike :groupName")
    Group findByName(@Param("groupName") String groupName);
}
