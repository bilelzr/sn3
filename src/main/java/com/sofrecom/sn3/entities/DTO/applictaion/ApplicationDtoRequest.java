package com.sofrecom.sn3.entities.DTO.applictaion;

import com.sofrecom.sn3.entities.DTO.group.GroupDtoRequest;

public class ApplicationDtoRequest {

    private String applicationName;

    private String applicanDescription;

    private GroupDtoRequest group;

    public ApplicationDtoRequest() {
    }

    public ApplicationDtoRequest(String applicationName, String applicanDescription, GroupDtoRequest group) {
        this.applicationName = applicationName;
        this.applicanDescription = applicanDescription;
        this.group = group;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicanDescription() {
        return applicanDescription;
    }

    public void setApplicanDescription(String applicanDescription) {
        this.applicanDescription = applicanDescription;
    }

    public GroupDtoRequest getGroup() {
        return group;
    }

    public void setGroup(GroupDtoRequest group) {
        this.group = group;
    }
}
