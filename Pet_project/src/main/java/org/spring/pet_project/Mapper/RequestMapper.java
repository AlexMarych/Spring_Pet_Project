package org.spring.pet_project.Mapper;

import org.mapstruct.*;
import org.spring.pet_project.Controller.DTO.Request.RequestBoardDto;
import org.spring.pet_project.Controller.DTO.Request.RequestTaskDto;
import org.spring.pet_project.Controller.DTO.Response.*;
import org.spring.pet_project.Model.*;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {IdMapper.class})
public interface RequestMapper {

    Board toBoard(RequestBoardDto boardDto);
    Board toBoard(RequestBoardDto boardDto, @MappingTarget Board board);

    Task toTask(RequestTaskDto taskDto);
    Task toTask(RequestTaskDto taskDto, @MappingTarget Task task);

    AppUser toAppUser(AppUserDto appUserDto);
    AppUser toAppUser(AppUserDto appUserDto, @MappingTarget AppUser appUser);
}
