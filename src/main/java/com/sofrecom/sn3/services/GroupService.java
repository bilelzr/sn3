package com.sofrecom.sn3.services;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoRequest;
import com.sofrecom.sn3.entities.DTO.group.GroupDtoResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface GroupService {

   void addUGroup(GroupDtoRequest groupDtoRequest);

   List<GroupDtoResponse> getAllGroups();

   GroupDtoResponse getGroupByName(String groupName );

   GroupDtoResponse modifyGroup(String groupName, GroupDtoRequest groupDtoRequest);

   boolean deleteGroup(String groupName);

   void affectMultiUserToGroup(List<UUID> uuidUsers, String groupName);

   void removeMultiUserFromGroup(List<UUID> uuidUsers, String groupName);


}
