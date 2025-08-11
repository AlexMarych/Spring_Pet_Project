package org.spring.pet_project.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.RequestAppUserDto;
import org.spring.pet_project.Controller.DTO.Response.AppUserDto;
import org.spring.pet_project.Exception.AppUserNotFoundException;
import org.spring.pet_project.Mapper.RequestMapper;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final ResponseMapper responseMapper;
    private final RequestMapper requestMapper;

    public AppUserDto getAppUserById(UUID id) {
        return responseMapper.toAppUserDto(appUserRepository.findById(id).orElseThrow(AppUserNotFoundException::new));
    }

    public List<AppUserDto> getAppUsersByBoardId(UUID boardId) {
        return appUserRepository.findAppUsersByBoardsId(boardId).stream().map(responseMapper::toAppUserDto).collect(Collectors.toList());
    }


    public AppUserDto updateAppUser(UUID id, RequestAppUserDto appUserDto) {
        var tmpAppUser = appUserRepository.findById(id).orElseThrow(AppUserNotFoundException::new);
        return responseMapper.toAppUserDto(appUserRepository.save(requestMapper.toAppUser(appUserDto, tmpAppUser)));
    }
}
