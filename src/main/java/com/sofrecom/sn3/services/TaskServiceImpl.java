package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.DTO.task.TaskDtoRequest;
import com.sofrecom.sn3.entities.DTO.task.TaskDtoResponse;
import com.sofrecom.sn3.entities.Task;
import com.sofrecom.sn3.entities.User;
import com.sofrecom.sn3.entities.enumeration.Priority;
import com.sofrecom.sn3.entities.enumeration.Status;
import com.sofrecom.sn3.exceptions.AffectationException;
import com.sofrecom.sn3.exceptions.TaskException;
import com.sofrecom.sn3.exceptions.UserNotFoundException;
import com.sofrecom.sn3.repositories.TaskRepository;
import com.sofrecom.sn3.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(TaskDtoRequest taskDtoRequest) {
        User createdBy = userRepository.findByUuid(UUID.fromString(taskDtoRequest.getUuidCreatedBy()));
        if (createdBy == null) {
            throw new UserNotFoundException("User not found");
        }
        Task task = new Task();
        task.setTaskName(taskDtoRequest.getTaskName());
        task.setTaskDescription(taskDtoRequest.getTaskDescription());
        task.setEcheance(taskDtoRequest.getEcheance());
        task.setStatus(Status.valueOf(taskDtoRequest.getStatus()));
        task.setPriority(Priority.valueOf(taskDtoRequest.getPriority()));
        task.setUuid(UUID.randomUUID());
        task.setCreatedAt(LocalDate.now());
        task.setLastModifiedAt(LocalDate.now());
        taskRepository.save(task);
    }

    @Override
    public List<TaskDtoResponse> getAllTasks() {
        List<TaskDtoResponse> taskDtoResponseList = new ArrayList<>();
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(task -> {
            TaskDtoResponse TaskDtoResponse = new TaskDtoResponse();
            TaskDtoResponse.setTaskName(task.getTaskName());
            TaskDtoResponse.setTaskDescription(task.getTaskDescription());
            TaskDtoResponse.setPorteur(DtoConverter.convertUserToDto(task.getPorteur()));
            taskDtoResponseList.add(TaskDtoResponse);
        });
        return taskDtoResponseList;
    }

    @Override
    public TaskDtoResponse getTaskByName(String taskName) {
        return null;
    }

    @Override
    public TaskDtoResponse modifyTask(String taskName, TaskDtoRequest taskDtoRequest) {
        Task taskToModify = taskRepository.findByName(taskName);
        if (taskToModify != null) {
            taskToModify.setTaskName(taskDtoRequest.getTaskName());
            taskToModify.setTaskDescription(taskDtoRequest.getTaskDescription());
            taskToModify.setEcheance(taskDtoRequest.getEcheance());
            taskToModify.setStatus(Status.valueOf(taskDtoRequest.getStatus()));
            taskToModify.setPriority(Priority.valueOf(taskDtoRequest.getPriority()));
            taskToModify.setLastModifiedAt(LocalDate.now());
            taskRepository.save(taskToModify);
            return DtoConverter.convertTaskToDto(taskToModify);
        }
        throw new TaskException("Task not found!");
    }

    @Override
    public boolean deleteTask(String taskName) {
        Task taskToDelete = taskRepository.findByName(taskName);
        if (taskToDelete != null) {
            taskRepository.delete(taskToDelete);
            return true;
        }
        return false;
    }

    @Override
    public void affectUserToTask(UUID uuidUser, String taskName) {

        User porteur = userRepository.findByUuid(uuidUser);
        Task task = taskRepository.findByName(taskName);
        if (task != null && porteur != null) {
            task.setPorteur(porteur);
            task.setLastModifiedAt(LocalDate.now());
            taskRepository.save(task);
        }
        throw new AffectationException("User or task not found!");
    }


    @Override
    public void removeUserFromTask(UUID uuidUser, String taskName) {

        User porteur = userRepository.findByUuid(uuidUser);
        Task task = taskRepository.findByName(taskName);
        if (task != null && porteur != null) {
            task.setPorteur(null);
            task.setStatus(Status.En_Attente);
            task.setLastModifiedAt(LocalDate.now());
            //TODO : send notification in case task without porteur
            taskRepository.save(task);
        }
        throw new AffectationException("User or task not found!");
    }

}