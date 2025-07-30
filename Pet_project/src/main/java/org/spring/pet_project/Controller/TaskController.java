package org.spring.pet_project.Controller;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.RequestTaskDto;
import org.spring.pet_project.Controller.DTO.Response.TaskDto;
import org.spring.pet_project.Service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskDto> getTask(@PathVariable UUID taskId) {
        return ResponseEntity.ok(taskService.getById(taskId));
    }

    @GetMapping("/{taskListId}")
    public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable UUID taskListId) {
        return ResponseEntity.ok(taskService.getAll(taskListId));
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@RequestBody RequestTaskDto createTaskDto) {
        return ResponseEntity.ok(taskService.createTask(createTaskDto));
    }

    @PutMapping("/{taskId}/update")
    public ResponseEntity<TaskDto> updateTaskDetails(@PathVariable UUID taskId, @RequestBody RequestTaskDto requestTaskDto) {
        return ResponseEntity.ok(taskService.updateTask(taskId, requestTaskDto));
    }

    @PutMapping("/{taskId}/migrate")
    public ResponseEntity<TaskDto> migrateTask(@PathVariable UUID taskId, @RequestParam UUID taskListId) {
        return ResponseEntity.ok(taskService.migrateTask(taskId, taskListId));
    }

    @PutMapping("/{taskId}/assign")
    public ResponseEntity<TaskDto> assignTask(@PathVariable UUID taskId, @RequestParam Set<UUID> memberIds) {
        return ResponseEntity.ok(taskService.assignTask(taskId, memberIds));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}
