package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.Application;
import com.sofrecom.sn3.entities.DTO.DtoConverter;
import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoRequest;
import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoResponse;
import com.sofrecom.sn3.entities.Group;
import com.sofrecom.sn3.exceptions.AffectationException;
import com.sofrecom.sn3.repositories.ApplicationRepository;
import com.sofrecom.sn3.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {


    private final ApplicationRepository applicationRepository;
    private final GroupRepository groupRepository;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, GroupRepository groupRepository) {
        this.applicationRepository = applicationRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    public void createApplication(ApplicationDtoRequest applicationDtoRequest) {
        Application application = new Application();
        application.setApplicationName(applicationDtoRequest.getApplicationName());
        application.setApplicationDescription(applicationDtoRequest.getApplicanDescription());
        application.setCreationDate(LocalDate.now());
        applicationRepository.save(application);
    }

    @Override
    public ApplicationDtoResponse getApplicationByName(String applicationName) {
        Application application = applicationRepository.findByName(applicationName);
        return DtoConverter.convertAppToDto(application);
    }

    @Override
    public List<ApplicationDtoResponse> getAllApplications() {

        List<ApplicationDtoResponse> appDtoResponseList = new ArrayList<>();
        List<Application> apps = applicationRepository.findAll();
        apps.forEach(application -> {
            ApplicationDtoResponse applicationDtoResponse = new ApplicationDtoResponse();
            applicationDtoResponse.setApplicationName(application.getApplicationName());
            applicationDtoResponse.setApplicationDescription(application.getApplicationDescription());
            if (application.getGroup() != null) {
                applicationDtoResponse.setGroup(DtoConverter.convertGroupToDto(application.getGroup()));
            }
            appDtoResponseList.add(applicationDtoResponse);

        });
        return appDtoResponseList;
    }

    @Override
    public ApplicationDtoResponse updateApplication(String applicationName, ApplicationDtoRequest applicationDtoRequest) {
        Application appToModify = applicationRepository.findByName(applicationName);
        Group group = groupRepository.findByName(applicationDtoRequest.getGroup().getGroupName());
        if (appToModify != null && group != null) {
            appToModify.setApplicationName(applicationDtoRequest.getApplicationName());
            appToModify.setApplicationDescription(applicationDtoRequest.getApplicanDescription());
            appToModify.setGroup(group);
            applicationRepository.save(appToModify);
            return DtoConverter.convertAppToDto(appToModify);
        }
        throw new AffectationException("App not found");
    }

    @Override
    public boolean deleteApplication(String applicationName) {
        Application appToDelete = applicationRepository.findByName(applicationName);
        if (appToDelete != null) {
            applicationRepository.delete(appToDelete);
            return true;
        }
        return false;
    }

    @Override
    public void assignGroupToApplication(UUID uuidGroup, String applicationName) {
        Group groupToAffect = groupRepository.findByUuid(uuidGroup);
        Application application = applicationRepository.findByName(applicationName);
        if (groupToAffect != null && application != null) {
            application.setGroup(groupToAffect);
            applicationRepository.save(application);
        }
        throw new AffectationException("App or group not found");

    }

    @Override
    public void removeGroupFromApplication(UUID uuidGroup, String applicationName) {
        Group group = groupRepository.findByUuid(uuidGroup);
        Application application = applicationRepository.findByName(applicationName);
        if (group != null && application != null) {
            application.setGroup(null);
            applicationRepository.save(application);
        }
        throw new AffectationException("App or group not found");
    }


}
