package com.sofrecom.sn3.entities.DTO.applictaion;

import com.sofrecom.sn3.entities.DTO.group.GroupDtoResponse;

public class ApplicationDtoResponse {


    private GroupDtoResponse group;
    private String applicationName;
    private String applicationDescription;


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
}
