package com.sofrecom.sn3.services;


import com.sofrecom.sn3.entities.DTO.DtoConverter;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoRequest;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoResponse;
import com.sofrecom.sn3.entities.Group;
import com.sofrecom.sn3.entities.User;
import com.sofrecom.sn3.exceptions.AffectationException;
import com.sofrecom.sn3.exceptions.GroupNotFoundException;
import com.sofrecom.sn3.repositories.GroupRepository;
import com.sofrecom.sn3.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupServiceImpl(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addGroup(GroupDtoRequest groupDtoRequest) {
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
            groupDtoResponse.setMembres(DtoConverter.convertListUserToDto(group.getMembres()));
            groupDtoResponseList.add(groupDtoResponse);

        });
        return groupDtoResponseList;
    }

    @Override
    public GroupDtoResponse getGroupByName(String groupName) {
        Group group = groupRepository.findByName(groupName);
        return DtoConverter.convertGroupToDto(group);
    }

    @Override
    public GroupDtoResponse modifyGroup(String groupName, GroupDtoRequest groupDtoRequest) {
        Group groupToModify = groupRepository.findByName(groupName);
        if (groupToModify != null) {
            groupToModify.setGroupName(groupDtoRequest.getGroupName());
            groupRepository.save(groupToModify);
            return DtoConverter.convertGroupToDto(groupToModify);
        }
        throw new GroupNotFoundException("Group not found");
    }

    @Override
    public boolean deleteGroup(String groupName) {
        Group groupToDelete = groupRepository.findByName(groupName);
        if (groupToDelete != null) {
            groupRepository.delete(groupToDelete);
            return true;
        }
        return false;
    }

    @Override
    public void affectMultiUserToGroup(List<UUID> uuidUsers, String groupName) {
        List<User> usersToAffect = userRepository.findByUuids(uuidUsers);
        Group group = groupRepository.findByName(groupName);
        if (!usersToAffect.isEmpty() && group != null) {
            group.setMembres(usersToAffect);
            groupRepository.save(group);
        }
        throw new AffectationException("Group or user not found");

    }

    @Override
    public void removeMultiUserFromGroup(List<UUID> uuidUsers, String groupName) {

        List<User> usersToRemove = userRepository.findByUuids(uuidUsers);
        Group group = groupRepository.findByName(groupName);
        if (!usersToRemove.isEmpty() && group != null) {
            List<User> existedUsers = group.getMembres();
            existedUsers.removeAll(usersToRemove);
            group.setMembres(existedUsers);
            groupRepository.save(group);
        }
        throw new AffectationException("Group or user not found");
    }
}
