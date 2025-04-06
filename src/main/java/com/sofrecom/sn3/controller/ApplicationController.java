package com.sofrecom.sn3.controller;


import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoRequest;
import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoResponse;
import com.sofrecom.sn3.services.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/app")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/byname/{applicationName}")
    public ResponseEntity<ApplicationDtoResponse> getApplicationByName(@PathVariable String applicationName) {
        return ResponseEntity.ok().body(applicationService.getApplicationByName(applicationName));
    }

    @PutMapping("/update/{applicationName}")
    public ResponseEntity<ApplicationDtoResponse> modifyApp(@RequestBody ApplicationDtoRequest applicationDtoRequest, @PathVariable String applicationName) {
        return ResponseEntity.ok().body(applicationService.updateApplication(applicationName, applicationDtoRequest));
    }

    @DeleteMapping("/delete/{applicationName}")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable String applicationName) {
        return ResponseEntity.ok().body(applicationService.deleteApplication(applicationName));
    }

    @PostMapping("/addGroup/{applicationName}/{uuidGroup}")
    public ResponseEntity affectGroupToApplication(@PathVariable String applicationName, @PathVariable String uuidGroup) {
        applicationService.assignGroupToApplication(UUID.fromString(uuidGroup), applicationName);
        return ResponseEntity.ok().body("Users affected successfully to application " + applicationName);
    }

    @PostMapping("/removeGroup/{applicationName}/{uuidGroup}")
    public ResponseEntity removeGroupFromApp(@PathVariable String applicationName, @PathVariable String uuidGroup) {
        applicationService.removeGroupFromApplication(UUID.fromString(uuidGroup), applicationName);
        return ResponseEntity.ok().body("Users removed successfully from group" + applicationName);
    }
}