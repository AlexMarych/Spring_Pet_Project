package org.spring.pet_project.Controller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Response.TaskListDto;
import org.spring.pet_project.Service.TaskListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/taskLists")
public class TaskListController {

    private final TaskListService taskListService;

    @GetMapping("/{boardId}")
    public ResponseEntity<List<TaskListDto>> getTaskLists(@PathVariable UUID boardId) {
        return ResponseEntity.ok(taskListService.getAll(boardId));
    }

    @PostMapping("/{boardId}")
    public ResponseEntity<TaskListDto> createTaskList(@PathVariable UUID boardId,
                                                      @RequestParam @NotBlank @Min(2) @Max(20) String title) {
        return ResponseEntity.ok(taskListService.createTaskList(boardId, title));
    }

    @PutMapping("/{taskListId}/update")
    public ResponseEntity<TaskListDto> updateTaskList(@PathVariable UUID taskListId,
                                                      @RequestParam @NotBlank @Min(2) @Max(20) String title){
        return ResponseEntity.ok(taskListService.updateTaskList(taskListId, title));
    }

    @PutMapping("/{taskListId}/add-tasks")
    public ResponseEntity<TaskListDto> updateTaskListTasks(@PathVariable UUID taskListId, @RequestParam @NotNull Set<UUID> taskIds) {
        return ResponseEntity.ok(taskListService.updateTasks(taskListId, taskIds));
    }

    @DeleteMapping("/{taskListId}")
    public ResponseEntity<Void> deleteTaskList(@PathVariable UUID taskListId) {
        taskListService.deleteTaskList(taskListId);
        return ResponseEntity.noContent().build();
    }


}
