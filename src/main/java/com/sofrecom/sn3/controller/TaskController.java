package com.sofrecom.sn3.controller;


import com.sofrecom.sn3.entities.DTO.task.TaskDtoRequest;
import com.sofrecom.sn3.entities.DTO.task.TaskDtoResponse;
import com.sofrecom.sn3.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/task")

public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @PostMapping("/add")
    public ResponseEntity addTask(@RequestBody TaskDtoRequest taskDtoRequest) {
        taskService.addTask(taskDtoRequest);
        return ResponseEntity.ok().body("Task created successfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<TaskDtoResponse>> getAllTasks() {
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }
    @GetMapping("/byname/{taskName}")
    public ResponseEntity<TaskDtoResponse> getTaskByName(@PathVariable String taskName){
        return ResponseEntity.ok().body(taskService.getTaskByName(taskName));
    }
    @PutMapping("/update/{taskName}")
    public ResponseEntity<TaskDtoResponse> modifyTask(@RequestBody TaskDtoRequest taskDtoRequest, @PathVariable String taskName) {
        return ResponseEntity.ok().body(taskService.modifyTask(taskName, taskDtoRequest));
    }

    @DeleteMapping("/delete/{taskName}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable String taskName) {
        return ResponseEntity.ok().body(taskService.deleteTask(taskName));
    }

    @PostMapping("/adduser/{taskName}")
    public ResponseEntity affectUserToGroup(@PathVariable String taskName, @RequestBody String uuid)
    {
        taskService.affectUserToTask(UUID.fromString(uuid), taskName);
        return ResponseEntity.ok().body("User affected successfully to task " + taskName);
    }

    @PostMapping("/removeuser/{taskName}")
    public ResponseEntity removeUserFromTask(@PathVariable String taskName, @RequestBody String uuid)
    {

        taskService.removeUserFromTask(UUID.fromString(uuid), taskName);
        return ResponseEntity.ok().body("User removed successfully from task" + taskName);
    }
}