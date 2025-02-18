package com.sofrecom.sn3.entities.DTO.group;

public class GroupDtoRequest {
    private String groupName;

    public GroupDtoRequest() {
    }

    public GroupDtoRequest(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
