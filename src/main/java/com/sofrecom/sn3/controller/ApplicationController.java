package com.sofrecom.sn3.controller;


import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoRequest;
import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoResponse;
import com.sofrecom.sn3.services.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/app")
@RestController
@CrossOrigin(origins = "*")
public class ApplicationController {
    private final ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/add")
    public ResponseEntity createApplication(@RequestBody ApplicationDtoRequest applicationDtoRequest) {
        applicationService.createApplication(applicationDtoRequest);
        return ResponseEntity.ok().body("Application created successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApplicationDtoResponse>> getAllApplications() {
        return ResponseEntity.ok().body(applicationService.getAllApplications());
    }

    @GetMapping("/byname/{appName}")
    public ResponseEntity<ApplicationDtoResponse> getApplicationByName(@PathVariable String applicationName) {
        return ResponseEntity.ok().body(applicationService.getApplicationByName(applicationName));
    }
    @PutMapping("/update/{appName}")
    public ResponseEntity<ApplicationDtoResponse> modifyApp(@RequestBody ApplicationDtoRequest applicationDtoRequest, @PathVariable String appicationName) {
        return ResponseEntity.ok().body(applicationService.updateApplication(appicationName, applicationDtoRequest));
    }

    @DeleteMapping("/delete/{appName}")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable String appicationName) {
        return ResponseEntity.ok().body(applicationService.deleteApplication(appicationName));
    }

    @PostMapping("/addusers/{appName}")
    public ResponseEntity affectMultiUserToApp(@PathVariable String applicationName, @RequestBody List<String> uuids)
    {
        List<UUID> uuidUsers = new ArrayList<>();
        uuids.forEach(uuid -> uuidUsers.add(UUID.fromString(uuid)));
        applicationService.assignUserToApplication(uuidUsers, applicationName);
        return ResponseEntity.ok().body("Users affected successfully to application " + applicationName);
    }

    @PostMapping("/removemultiuser/{appName}")
    public ResponseEntity removeMultiUserFromApp(@PathVariable String applicationName, @RequestBody List<String> uuids)
    {
        List<UUID> uuidUsers = new ArrayList<>();
        uuids.forEach(uuid -> uuidUsers.add(UUID.fromString(uuid)));
        applicationService.removeMultiMemberFromApp(uuidUsers, applicationName);
        return ResponseEntity.ok().body("Users removed successfully from group" + applicationName);
    }
}