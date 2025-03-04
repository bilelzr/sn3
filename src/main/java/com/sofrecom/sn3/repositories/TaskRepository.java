package com.sofrecom.sn3.repositories;

import com.sofrecom.sn3.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Query("select task from Task task where task.taskName ilike :taskName")
    Task findByName(@Param("taskName") String taskName);
}
