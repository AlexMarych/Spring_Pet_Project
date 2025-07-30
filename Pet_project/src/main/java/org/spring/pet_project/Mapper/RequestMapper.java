package org.spring.pet_project.Mapper;

import org.mapstruct.*;
import org.spring.pet_project.Controller.DTO.Request.RequestBoardDto;
import org.spring.pet_project.Controller.DTO.Request.RequestTaskDto;
import org.spring.pet_project.Controller.DTO.Response.*;
import org.spring.pet_project.Model.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RequestMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "listsOfTasks", ignore = true)
    @Mapping(target = "chats", ignore = true)
    @Mapping(target = "ownerAppUser", ignore = true)
    @Mapping(target = "memberAppUsers", ignore = true)
    Board toBoard(RequestBoardDto boardDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "listsOfTasks", ignore = true)
    @Mapping(target = "chats", ignore = true)
    @Mapping(target = "ownerAppUser", ignore = true)
    @Mapping(target = "memberAppUsers", ignore = true)
    Board toBoard(RequestBoardDto boardDto, @MappingTarget Board board);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "listOfTasks", ignore = true)
    @Mapping(target = "checkStatuses", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "appUsers", ignore = true)
    Task toTask(RequestTaskDto taskDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "listOfTasks", ignore = true)
    @Mapping(target = "checkStatuses", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "appUsers", ignore = true)
    Task toTask(RequestTaskDto taskDto, @MappingTarget Task task);

    @Mapping(target = "ownedBoards", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "checkStatus", ignore = true)
    @Mapping(target = "boards", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    AppUser toAppUser(AppUserDto appUserDto);

    @Mapping(target = "ownedBoards", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "checkStatus", ignore = true)
    @Mapping(target = "boards", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    AppUser toAppUser(AppUserDto appUserDto, @MappingTarget AppUser appUser);
}
