package com.sofrecom.sn3.services;

import com.sofrecom.sn3.entities.DTO.UserDto;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoResponse;
import com.sofrecom.sn3.entities.Group;
import com.sofrecom.sn3.entities.User;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {

    public static GroupDtoResponse convertGroupToDto(Group group) {
        GroupDtoResponse dto = new GroupDtoResponse();
        dto.setUuid(group.getUuid());
        dto.setGroupName(group.getGroupName());
        //dto.setMembres();
        return dto;
    }

    public static List<GroupDtoResponse> convertListGroupToDto (List<Group> groups) {
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
        userDto.setPhone(user.getPhone());
        userDto.setAccountNonLocked(user.isAccountNonLocked());
        return userDto;
    }

    public static List<UserDto> convertListUserToDto (List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        users.forEach(user -> userDtoList.add(convertUserToDto(user)));
        return userDtoList;
    }


}
