package com.sofrecom.sn3.entities.DTO.group;

import com.sofrecom.sn3.entities.DTO.user.UserDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class GroupDtoResponse {

    private String groupName;

    private List<UserDto> membres;

    private UUID uuid;

    private LocalDate creationDate;

    public GroupDtoResponse() {
    }

    public GroupDtoResponse(String groupName, List<UserDto> membres, UUID uuid) {
        this.groupName = groupName;
        this.membres = membres;
        this.uuid = uuid;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<UserDto> getMembres() {
        return membres;
    }

    public void setMembres(List<UserDto> membres) {
        this.membres = membres;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
