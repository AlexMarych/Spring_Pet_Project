package org.spring.pet_project.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.spring.pet_project.Controller.DTO.Response.*;
import org.spring.pet_project.Model.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ResponseMapper {
    AppUserDto toAppUserDto(AppUser appUser);

    @Mapping(target = "listsOfTasksIds",
            expression="java(board.getListsOfTasks()" +
            ".stream().map(TaskList::getId)" +
            ".collect(Collectors.toSet()))")
    @Mapping(target = "chatIds",
            expression="java(board.getChats()" +
            ".stream().map(Chat::getId)" +
            ".collect(Collectors.toSet()))")
    @Mapping(target = "ownerAppUserId", expression = "java(board.getOwnerAppUser().getId())")
    @Mapping(target = "memberAppUserIds",
            expression="java(board.getMemberAppUsers()" +
                    ".stream().map(AppUser::getId)" +
                    ".collect(Collectors.toSet()))")
    BoardDto toBoardDto(Board board);

    @Mapping(target = "boardId", expression = "java(chat.getBoard().getId())")
    @Mapping(target = "messageIds",
            expression="java(chat.getMessages()" +
            ".stream().map(ChatMessage::getId)" +
            ".collect(Collectors.toSet()))")
    ChatDto toChatDto(Chat chat);

    CheckStatusDto toCheckStatusDto(CheckStatus checkStatus);

    @Mapping(target = "appUserId", expression = "java(commentMessage.getAppUser().getId())")
    ChatMessageDto toMessageDto(ChatMessage chatMessage);

    @Mapping(target = "appUserId", expression = "java(commentMessage.getAppUser().getId())")
    CommentMessageDto toMessageDto(CommentMessage commentMessage);

    @Mapping(target = "checkStatusIds",
            expression="java(task.getCheckStatuses()" +
                    ".stream().map(CheckStatus::getId)" +
                    ".collect(Collectors.toSet()))")
    @Mapping(target = "messageIds",
            expression="java(task.getMessages()" +
                    ".stream().map(CommentMessage::getId)" +
                    ".collect(Collectors.toSet()))")
    TaskDto toTaskDto(Task task);

    @Mapping(target = "taskIds",
            expression="java(taskList.getTasks()" +
                    ".stream().map(Task::getId)" +
                    ".collect(Collectors.toSet()))")
    TaskListDto toTaskListDto(TaskList taskList);

}
