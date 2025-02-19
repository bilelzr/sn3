package com.sofrecom.sn3.controller;

import com.sofrecom.sn3.entities.DTO.UserDto;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoRequest;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoResponse;
import com.sofrecom.sn3.services.GroupService;
import com.sofrecom.sn3.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/group")

public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }
    @PostMapping("/add")
    public ResponseEntity addGroup(@RequestBody GroupDtoRequest groupDtoRequest) {
        groupService.addGroup(groupDtoRequest);
        return ResponseEntity.ok().body("Group created successfully");
    }
    @GetMapping("/all")
    public ResponseEntity<List<GroupDtoResponse>> getAllGroups() {
        return ResponseEntity.ok().body(groupService.getAllGroups());
    }
    @GetMapping("/byname/{groupName}")
    public ResponseEntity<GroupDtoResponse> getGroupByName(@PathVariable String groupName){
        return ResponseEntity.ok().body(groupService.getGroupByName(groupName));
    }
    @PutMapping("/update/{groupName}")
    public ResponseEntity<GroupDtoResponse> modifyGroup(@RequestBody GroupDtoRequest groupDtoRequest, @PathVariable String groupName) {
        return ResponseEntity.ok().body(groupService.modifyGroup(groupName, groupDtoRequest));
    }

    @DeleteMapping("/delete/{groupName}")
    public ResponseEntity<Boolean> deleteGroup(@PathVariable String groupName) {
        return ResponseEntity.ok().body(groupService.deleteGroup(groupName));
    }

    @PostMapping("/addusers/{groupName}")
    public ResponseEntity affectMultiUserToGroup(@PathVariable String groupName, @RequestBody List<String> uuids)
    {
        List<UUID> uuidUsers = new ArrayList<>();
        uuids.forEach(uuid -> uuidUsers.add(UUID.fromString(uuid)));
        groupService.affectMultiUserToGroup(uuidUsers, groupName);
        return ResponseEntity.ok().body("Users affected successfully to group " + groupName);
    }

    @PostMapping("/removemultiuser/{groupName}")
    public ResponseEntity removeMultiUserFromGroup(@PathVariable String groupName, @RequestBody List<String> uuids)
    {
        List<UUID> uuidUsers = new ArrayList<>();
        uuids.forEach(uuid -> uuidUsers.add(UUID.fromString(uuid)));
        groupService.removeMultiUserFromGroup(uuidUsers, groupName);
        return ResponseEntity.ok().body("Users removed successfully from group" + groupName);
    }
}
