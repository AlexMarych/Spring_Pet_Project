package org.spring.pet_project.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.spring.pet_project.Controller.DTO.Response.*;
import org.spring.pet_project.Model.*;
import org.spring.pet_project.Model.Messages.ChatMessage;
import org.spring.pet_project.Model.Messages.CommentMessage;
import org.spring.pet_project.Model.Messages.Message;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ResponseMapper {

    @Mapping(target = "ownedBoardIds",
            expression="java(appUser.getOwnedBoards()" +
                    ".stream().map(Board::getId)" +
                    ".collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "boardIds",
            expression="java(appUser.getBoards()" +
                    ".stream().map(Board::getId)" +
                    ".collect(java.util.stream.Collectors.toSet()))")
    AppUserDto toAppUserDto(AppUser appUser);

    @Mapping(target = "listsOfTasksIds",
            expression="java(board.getListsOfTasks()" +
            ".stream().map(TaskList::getId)" +
            ".collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "chatIds",
            expression="java(board.getChats()" +
            ".stream().map(Chat::getId)" +
            ".collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "ownerAppUserId", expression = "java(board.getOwnerAppUser().getId())")
    @Mapping(target = "memberAppUserIds",
            expression="java(board.getMemberAppUsers()" +
                    ".stream().map(AppUser::getId)" +
                    ".collect(java.util.stream.Collectors.toSet()))")
    BoardDto toBoardDto(Board board);

    @Mapping(target = "boardId", expression = "java(chat.getBoard().getId())")
    @Mapping(target = "messageIds",
            expression="java(chat.getMessages()" +
            ".stream().map(ChatMessage::getId)" +
            ".collect(java.util.stream.Collectors.toSet()))")
    ChatDto toChatDto(Chat chat);

    CheckStatusDto toCheckStatusDto(CheckStatus checkStatus);

    @Mapping(target = "appUserId", expression = "java(chatMessage.getAppUser().getId())")
    ChatMessageDto toChatMessageDto(ChatMessage chatMessage);

    @Mapping(target = "appUserId", expression = "java(commentMessage.getAppUser().getId())")
    CommentMessageDto toCommentDto(CommentMessage commentMessage);

    @Mapping(target = "appUserId", expression = "java(message.getAppUser().getId())")
    ChatMessageDto toChatMessageDto(Message message);
    @Mapping(target = "appUserId", expression = "java(message.getAppUser().getId())")
    CommentMessageDto toCommentMessageDto(Message message);

    @Mapping(target = "checkStatusIds",
            expression="java(task.getCheckStatuses()" +
                    ".stream().map(CheckStatus::getId)" +
                    ".collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "messageIds",
            expression="java(task.getMessages()" +
                    ".stream().map(CommentMessage::getId)" +
                    ".collect(java.util.stream.Collectors.toSet()))")
    TaskDto toTaskDto(Task task);

    @Mapping(target = "taskIds",
            expression="java(taskList.getTasks()" +
                    ".stream().map(Task::getId)" +
                    ".collect(java.util.stream.Collectors.toSet()))")
    TaskListDto toTaskListDto(TaskList taskList);
}
