package org.spring.pet_project.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.spring.pet_project.Controller.DTO.Request.RequestAppUserDto;
import org.spring.pet_project.Controller.DTO.Request.RequestBoardDto;
import org.spring.pet_project.Controller.DTO.Request.RequestTaskDto;
import org.spring.pet_project.Controller.DTO.Request.SignUpRequestDto;
import org.spring.pet_project.Model.AppUser;
import org.spring.pet_project.Model.Board;
import org.spring.pet_project.Model.Task;


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

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "ownedBoards", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "checkStatus", ignore = true)
    @Mapping(target = "boards", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    AppUser toAppUser(RequestAppUserDto appUserDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "ownedBoards", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "checkStatus", ignore = true)
    @Mapping(target = "boards", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    AppUser toAppUser(RequestAppUserDto appUserDto, @MappingTarget AppUser appUser);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "ownedBoards", ignore = true)
    @Mapping(target = "messages", ignore = true)
    @Mapping(target = "checkStatus", ignore = true)
    @Mapping(target = "boards", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "name",  ignore = true)
    AppUser toAppUser(SignUpRequestDto signUpRequestDto);
}
