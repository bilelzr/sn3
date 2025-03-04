package com.sofrecom.sn3.entities;

import com.sofrecom.sn3.entities.enumeration.Priority;
import com.sofrecom.sn3.entities.enumeration.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1, initialValue = 1)
    private long taskPkId;
    @Column(unique = true, nullable = false)
    private String taskName;
    @ManyToOne
    @JoinColumn(name = "user_pk_id", nullable = false) // Foreign key column in the task table
    private User porteur;
    @ManyToOne
    @JoinColumn(name = "user_pk_id", nullable = false) // Foreign key column in the task table
    private User createdBy;
    private UUID uuid;
    private String taskDescription;
    private Priority priority;
    private Status status;
    private LocalDate echeance;
    private LocalDate createdAt;
    private LocalDate lastModifiedAt;


    public Task() {
    }

    public Task(long taskPkId, String taskName, User porteur, User createdBy, UUID uuid, String taskDescription, Priority priority, Status status, LocalDate echeance, LocalDate createdAt, LocalDate lastModifiedAt) {
        this.taskPkId = taskPkId;
        this.taskName = taskName;
        this.porteur = porteur;
        this.createdBy = createdBy;
        this.uuid = uuid;
        this.taskDescription = taskDescription;
        this.priority = priority;
        this.status = status;
        this.echeance = echeance;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public void setEcheance(LocalDate echeance) {
        this.echeance = echeance;
    }

    public LocalDate getEcheance() {
        return echeance;
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

    public long getTaskPkId() {
        return taskPkId;
    }

    public void setTaskPkId(long taskPkId) {
        this.taskPkId = taskPkId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public User getPorteur() {
        return porteur;
    }

    public void setPorteur(User porteur) {
        this.porteur = porteur;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


}

