package org.spring.pet_project.Controller;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Response.CheckStatusDto;
import org.spring.pet_project.Model.Enumeration.Status;
import org.spring.pet_project.Service.CheckStatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/check-statuses")
public class CheckStatusController {

    private final CheckStatusService checkStatusService;

    @GetMapping("/{checkId}")
    public ResponseEntity<CheckStatusDto> getCheckStatus(@PathVariable UUID checkId) {
        return ResponseEntity.ok(checkStatusService.getById(checkId));
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<List<CheckStatusDto>> getAllCheckStatuses(@PathVariable UUID taskId) {
        return ResponseEntity.ok(checkStatusService.getAll(taskId));
    }

    @PostMapping("/{taskId}") //TODO: add user reference
    public ResponseEntity<CheckStatusDto> createCheckStatus(@PathVariable UUID taskId) {
        return ResponseEntity.ok(checkStatusService.createCheckStatus(taskId));
    }

    @PutMapping("/{taskId}") //TODO: add user reference
    public ResponseEntity<CheckStatusDto> updateCheckStatus(@PathVariable UUID taskId, @RequestParam Status status) {
        return ResponseEntity.ok(checkStatusService.updateCheckStatus(taskId, status));
    }

    @DeleteMapping("/{checkId}")
    public ResponseEntity<Void> deleteCheckStatus(@PathVariable UUID checkId) {
        checkStatusService.deleteCheckStatus(checkId);
        return ResponseEntity.ok().build();
    }

}
