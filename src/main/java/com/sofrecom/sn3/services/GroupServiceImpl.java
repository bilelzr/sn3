package com.sofrecom.sn3.services;


import com.sofrecom.sn3.entities.DTO.group.GroupDtoRequest;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoResponse;
import com.sofrecom.sn3.entities.Group;
import com.sofrecom.sn3.repositories.GroupRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {


    private final GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public void addUGroup(GroupDtoRequest groupDtoRequest) {
        Group group = new Group();
        group.setGroupName(groupDtoRequest.getGroupName());
        group.setUuid(UUID.randomUUID());
        groupRepository.save(group);
    }

    @Override
    public List<GroupDtoResponse> getAllGroups() {
        List<GroupDtoResponse> groupDtoResponseList = new ArrayList<>();
        List<Group> groups = groupRepository.findAll();
        groups.forEach(group -> {
            GroupDtoResponse groupDtoResponse = new GroupDtoResponse();
            groupDtoResponse.setGroupName(group.getGroupName());
            groupDtoResponse.setUuid(group.getUuid());
           // TODO// groupDtoResponse.setMembres(group.getMembres());
        });
        return List.of();
    }

    @Override
    public GroupDtoResponse getGroupByName(String groupName) {
        return null;
    }

    @Override
    public GroupDtoResponse modifyGroup(String groupName, GroupDtoRequest groupDtoRequest) {
        return null;
    }

    @Override
    public void deleteGroup(String groupName) {

    }

    @Override
    public void affectMultiUserToGroup(List<UUID> uuidUsers, String groupName) {

    }

    @Override
    public void removeMultiUserFromGroup(List<UUID> uuidUsers, String groupName) {

    }
}
