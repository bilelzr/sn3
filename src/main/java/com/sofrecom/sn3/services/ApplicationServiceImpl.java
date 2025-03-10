package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.Application;
import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoRequest;
import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoResponse;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoResponse;
import com.sofrecom.sn3.entities.Group;
import com.sofrecom.sn3.entities.User;
import com.sofrecom.sn3.exceptions.AffectationException;
import com.sofrecom.sn3.exceptions.GroupNotFoundException;
import com.sofrecom.sn3.repositories.ApplicationRepository;
import com.sofrecom.sn3.repositories.GroupRepository;
import com.sofrecom.sn3.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {


    private final ApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ApplicationDtoResponse applicationDtoResponse;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, UserRepository userRepository, GroupRepository groupRepository, ApplicationDtoResponse applicationDtoResponse) {
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.applicationDtoResponse = applicationDtoResponse;
    }

    @Override
    public void createApplication(ApplicationDtoRequest applicationDtoRequest) {
        Application application = new Application();
        application.setApplicationName(applicationDtoRequest.getApplicationName());
        application.setApplicationDescription(applicationDtoRequest.getApplicanDescription());

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
            applicationDtoResponse.setApplicationMembers(DtoConverter.convertListUserToDto(application.getGroup().getMembres()));
            appDtoResponseList.add(applicationDtoResponse);

        });
        return appDtoResponseList;
    }

    @Override
    public ApplicationDtoResponse updateApplication(String applicationName, ApplicationDtoRequest applicationDtoRequest) {
        Application appToModify = applicationRepository.findByName(applicationName);
        if (appToModify != null) {
            appToModify.setApplicationName(applicationDtoRequest.getApplicationName());
            appToModify.setApplicationDescription(applicationDtoRequest.getApplicanDescription());
            applicationDtoResponse.setApplicationMembers(DtoConverter.convertListUserToDto(appToModify.getGroup().getMembres()));
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
    public void assignUserToApplication(List<UUID> uuidUsers, String applicationName) {

        List<User> membersToAffect = userRepository.findByUuids(uuidUsers);
        Application application = applicationRepository.findByName(applicationName);
        if (!membersToAffect.isEmpty() && application != null) {
            application.getGroup().getMembres();
            applicationRepository.save(application);
        }
        throw new AffectationException("App or user not found");

    }

    @Override
    public void removeMultiMemberFromApp(List<UUID> uuidUsers, String applicationName) {
        List<User> memberToRemove = userRepository.findByUuids(uuidUsers);
            Application application = applicationRepository.findByName(applicationName);
        if (!memberToRemove.isEmpty() && application != null) {
            List<User> existedUsers = application.getGroup().getMembres();
            existedUsers.removeAll(memberToRemove);
            application.getGroup().setMembres(existedUsers);
            applicationRepository.save(application);
        }
        throw new AffectationException("App or user not found");
    }


}
