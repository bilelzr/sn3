package com.sofrecom.sn3.entities.DTO.applictaion;

import com.sofrecom.sn3.entities.DTO.UserDto;

import java.util.List;

public class ApplicationDtoResponse {


    private List<UserDto> applicationMembers;
    private String applicationName;
    private String applicationDescription;

    public ApplicationDtoResponse(List<UserDto> applicationMembers, String applicationName, String applicationDescription) {
        this.applicationMembers = applicationMembers;
        this.applicationName = applicationName;
        this.applicationDescription = applicationDescription;
    }

    public ApplicationDtoResponse() {
    }


    public List<UserDto> getApplicationMembers() {
        return applicationMembers;
    }

    public void setApplicationMembers(List<UserDto> applicationMembers) {
        this.applicationMembers = applicationMembers;
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
