package com.sofrecom.sn3.entities.DTO.task;

import java.time.LocalDate;

public class TaskDtoRequest {

    private String taskName;
    private String taskDescription;
    private String status;
    private LocalDate echeance;
    private String uuidPorteur;
    private String priority;
    private String uuidCreatedBy;

    public TaskDtoRequest() {
    }

    public TaskDtoRequest(String taskName, String taskDescription, String status, LocalDate echeance, String uuidPorteur, String priority, String uuidCreatedBy) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
        this.echeance = echeance;
        this.uuidPorteur = uuidPorteur;
        this.priority = priority;
        this.uuidCreatedBy = uuidCreatedBy;
    }

    public String getUuidCreatedBy() {
        return uuidCreatedBy;
    }

    public void setUuidCreatedBy(String uuidCreatedBy) {
        this.uuidCreatedBy = uuidCreatedBy;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }


    public LocalDate getEcheance() {
        return echeance;
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public String getUuidPorteur() {
        return uuidPorteur;
    }

    public void setUuidPorteur(String uuidPorteur) {
        this.uuidPorteur = uuidPorteur;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
