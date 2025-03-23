package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.Application;
import com.sofrecom.sn3.entities.DTO.UserDto;
import com.sofrecom.sn3.entities.DTO.applictaion.ApplicationDtoResponse;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoResponse;
import com.sofrecom.sn3.entities.DTO.task.TaskDtoResponse;
import com.sofrecom.sn3.entities.Group;
import com.sofrecom.sn3.entities.Task;
import com.sofrecom.sn3.entities.User;
import com.sofrecom.sn3.entities.enumeration.Role;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {

    public static TaskDtoResponse convertTaskToDto(Task task) {
        TaskDtoResponse dto = new TaskDtoResponse();
        dto.setTaskDescription(task.getTaskDescription());
        dto.setTaskName(task.getTaskName());
        dto.setPorteur(convertUserToDto(task.getPorteur()));
        dto.setPriority(task.getPriority());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setLastModifiedAt(task.getLastModifiedAt());
        dto.setUuid(task.getUuid());
        return dto;
    }

    public static List<TaskDtoResponse> convertTaskToDto(List<Task> tasks) {
        List<TaskDtoResponse> taskDtoResponseList = new ArrayList<>();
        tasks.forEach(task -> taskDtoResponseList.add(convertTaskToDto(task)));
        return taskDtoResponseList;
    }

    public static GroupDtoResponse convertGroupToDto(Group group) {
        GroupDtoResponse dto = new GroupDtoResponse();
        dto.setUuid(group.getUuid());
        dto.setGroupName(group.getGroupName());
        //dto.setMembres();
        return dto;
    }

    public static ApplicationDtoResponse convertAppToDto(Application application) {
        ApplicationDtoResponse dto = new ApplicationDtoResponse();
        dto.setApplicationDescription(application.getApplicationDescription());
        dto.setApplicationName(application.getApplicationName());
        //dto.setMembres();
        return dto;
    }

    public static List<GroupDtoResponse> convertListGroupToDto(List<Group> groups) {
        List<GroupDtoResponse> groupDtoResponseList = new ArrayList<>();
        groups.forEach(group -> groupDtoResponseList.add(convertGroupToDto(group)));
        return groupDtoResponseList;
    }

    public static UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUuid(user.getUuid());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole().toString());
        userDto.setPhone(user.getPhone());
        userDto.setAccountNonLocked(user.isAccountNonLocked());
        return userDto;
    }

    public static List<UserDto> convertListUserToDto(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        users.forEach(user -> userDtoList.add(convertUserToDto(user)));
        return userDtoList;
    }


}
