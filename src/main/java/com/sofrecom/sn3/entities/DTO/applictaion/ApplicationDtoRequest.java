package com.sofrecom.sn3.entities.DTO.applictaion;

public class ApplicationDtoRequest {

    private String applicationName;

    private String applicanDescription;

    public String getApplicationName() {
        return applicationName;
    }

    public ApplicationDtoRequest(String applicanDescription, String applicationName) {
        this.applicanDescription = applicanDescription;
        this.applicationName = applicationName;
    }

    public String getApplicanDescription() {
        return applicanDescription;
    }

    public void setApplicanDescription(String applicanDescription) {
        this.applicanDescription = applicanDescription;
    }

    public ApplicationDtoRequest() {
    }

    public ApplicationDtoRequest(String applicationName) {
        this.applicationName = applicationName;
    }
}
