package com.sofrecom.sn3.entities.DTO.applictaion;

import com.sofrecom.sn3.entities.DTO.group.GroupDtoResponse;

import java.time.LocalDate;
import java.util.UUID;

public class ApplicationDtoResponse {


    private GroupDtoResponse group;
    private String applicationName;
    private String applicationDescription;
    private LocalDate creationDate;
    private UUID uuid;


    public ApplicationDtoResponse(GroupDtoResponse group, String applicationName, String applicationDescription) {
        this.group = group;
        this.applicationName = applicationName;
        this.applicationDescription = applicationDescription;
    }

    public ApplicationDtoResponse() {
    }


    public GroupDtoResponse getGroup() {
        return group;
    }

    public void setGroup(GroupDtoResponse group) {
        this.group = group;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationDescription() {
        return applicationDescription;
    }

    public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
