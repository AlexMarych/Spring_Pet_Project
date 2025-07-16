package org.spring.pet_project.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.spring.pet_project.Controller.DTO.Request.Update.*;
import org.spring.pet_project.Controller.DTO.Response.*;
import org.spring.pet_project.Model.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {IdMapper.class})
public interface UpdateRequestMapper {
    @Mapping(target = "memberAppUsers" , source = "memberIds")
    Board toBoard(UpdateBoardDto boardDto, @MappingTarget Board board);

    Chat toChat(UpdateChatDto chatDto, @MappingTarget Chat chat);

    ChatMessage toChatMessage(UpdateChatMessageDto chatMessageDto, @MappingTarget ChatMessage chatMessage);

    CommentMessage toCommentMessage(UpdateCommentDto commentMessageDto, @MappingTarget CommentMessage commentMessage);

    @Mapping(target = "listOfTasks", source = "listOfTasksId")
    @Mapping(target = "appUsers", source = "memberIds")
    Task toTask(UpdateTaskDto taskDto, @MappingTarget Task task);

    TaskList toTaskList(UpdateTaskListDto taskListDto, @MappingTarget TaskList taskList);

    CheckStatus toCheckStatus(UpdateCheckStatusDto checkStatusDto, @MappingTarget CheckStatus checkStatus);

    AppUser toAppUser(AppUserDto appUserDto, @MappingTarget AppUser appUser);

}
