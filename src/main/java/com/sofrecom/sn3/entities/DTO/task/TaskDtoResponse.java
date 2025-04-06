package com.sofrecom.sn3.entities.DTO.task;

import com.sofrecom.sn3.entities.DTO.user.UserDto;
import com.sofrecom.sn3.entities.enumeration.Priority;

import java.time.LocalDate;
import java.util.UUID;

public class TaskDtoResponse {

    private String taskName;
    private String taskDescription;
    private UserDto porteur;
    private String status;
    private UUID uuid;
    private UserDto createdBy;
    private Priority priority;
    private LocalDate echeance;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;

    public TaskDtoResponse(String taskName, String taskDescription, UserDto porteur, String status, UUID uuid, Priority priority, LocalDate echeance, LocalDate createdAt, LocalDate lastModifiedAt) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.porteur = porteur;
        this.status = status;
        this.uuid = uuid;
        this.priority = priority;
        this.echeance = echeance;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public TaskDtoResponse(String taskName, String taskDescription, UserDto porteur, String status, UUID uuid, UserDto createdBy, Priority priority, LocalDate echeance, LocalDate createdAt, LocalDate lastModifiedAt) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.porteur = porteur;
        this.status = status;
        this.uuid = uuid;
        this.createdBy = createdBy;
        this.priority = priority;
        this.echeance = echeance;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public TaskDtoResponse() {
    }

    public UserDto getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDto createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDate getEcheance() {
        return echeance;
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDate lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public UserDto getPorteur() {
        return porteur;
    }

    public void setPorteur(UserDto porteur) {
        this.porteur = porteur;
    }
}
