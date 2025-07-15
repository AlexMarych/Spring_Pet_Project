package org.spring.pet_project.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.spring.pet_project.Controller.DTO.Request.Update.*;
import org.spring.pet_project.Controller.DTO.Response.*;
import org.spring.pet_project.Model.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {IdMapper.class})
public interface UpdateRequestMapper {
    @Mapping(target = "memberAppUsers" , source = "memberIds")
    Board toBoard(UpdateBoardDto boardDto);

    ChatMessage toChatMessage(UpdateChatMessageDto chatMessageDto);

    CommentMessage toCommentMessage(UpdateCommentDto commentMessageDto);

    @Mapping(target = "listOfTasks", source = "listOfTasksId")
    @Mapping(target = "appUsers", source = "memberIds")
    Task toTask(UpdateTaskDto taskDto);

    TaskList toTaskList(UpdateTaskListDto taskListDto);

    CheckStatus toCheckStatus(UpdateCheckStatusDto checkStatusDto);

    AppUser toAppUser(AppUserDto appUserDto);

}
