package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.DTO.task.TaskDtoRequest;
import com.sofrecom.sn3.entities.DTO.task.TaskDtoResponse;

import java.util.List;
import java.util.UUID;

public interface TaskService {


    void addTask(TaskDtoRequest taskDtoRequest);

    List<TaskDtoResponse> getAllTasks();

    TaskDtoResponse getTaskByName(String taskName );

    TaskDtoResponse modifyTask(String taskName, TaskDtoRequest taskDtoRequest);

    boolean deleteTask(String taskName);

    void affectUserToTask(UUID uuidUser, String taskName);

    void removeUserFromTask(UUID uuidUser, String taskName);

}
