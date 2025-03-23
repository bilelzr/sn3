package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoRequest;
import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoResponse;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {

    void createApplication(ApplicationDtoRequest applicationDtoRequest);

    ApplicationDtoResponse getApplicationByName(String applicationName);

    List<ApplicationDtoResponse> getAllApplications();

    ApplicationDtoResponse updateApplication(String applicationName, ApplicationDtoRequest applicationDtoRequest);

    boolean deleteApplication(String applicationName);

    void assignGroupToApplication(UUID uuidGroup, String applicationName);

    void removeGroupFromApplication(UUID uuidGroup, String applicationName);

}
