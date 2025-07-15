package org.spring.pet_project.Mapper;

import org.mapstruct.*;
import org.spring.pet_project.Controller.DTO.Request.Create.*;
import org.spring.pet_project.Controller.DTO.Response.*;
import org.spring.pet_project.Model.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {IdMapper.class})
public interface CreateRequestMapper {

    @Mapping(target = "memberAppUsers" , source = "memberIds")
    Board toBoard(CreateBoardDto boardDto);

    @Mapping(target = "chat", source = "chatId")
    ChatMessage toChatMessage(CreateChatMessageDto chatMessageDto);

    @Mapping(target = "task", source = "taskId")
    CommentMessage toCommentMessage(CreateCommentDto commentMessageDto);

    @Mapping(target = "listOfTasks", source = "listOfTasksId")
    @Mapping(target = "appUsers", source = "memberIds")
    Task toTask(CreateTaskDto taskDto);

    @Mapping(target = "board", source = "boardId")
    TaskList toTaskList(CreateTaskListDto taskListDto);

    AppUser toAppUser(AppUserDto appUserDto);

}
