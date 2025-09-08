package org.spring.pet_project.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.RequestAppUserDto;
import org.spring.pet_project.Controller.DTO.Response.AppUserDto;
import org.spring.pet_project.Service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app-users")
public class AppUserController{

    private final AppUserService appUserService;

    @GetMapping("/{app-user-id}")
    public ResponseEntity<AppUserDto> getAppUser(@PathVariable("app-user-id") UUID id){
        return ResponseEntity.ok(appUserService.getAppUserById(id));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<List<AppUserDto>> getAppUserByBoardId(@PathVariable UUID boardId){
        return ResponseEntity.ok(appUserService.getAppUsersByBoardId(boardId));
    }

    @PutMapping("/{app-user-id}")
    public ResponseEntity<AppUserDto> updateAppUser(@PathVariable("app-user-id") UUID id, @Valid @RequestBody RequestAppUserDto appUserDto){
        return ResponseEntity.ok(appUserService.updateAppUser(id, appUserDto));
    }

}
