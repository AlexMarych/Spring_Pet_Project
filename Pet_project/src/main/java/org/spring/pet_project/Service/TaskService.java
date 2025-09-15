package org.spring.pet_project.Service;

import lombok.AllArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.RequestTaskDto;
import org.spring.pet_project.Controller.DTO.Response.TaskDto;
import org.spring.pet_project.Mapper.RequestMapper;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Repository.AppUserRepository;
import org.spring.pet_project.Repository.TaskListRepository;
import org.spring.pet_project.Repository.TaskRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ResponseMapper responseMapper;
    private final RequestMapper requestMapper;
    private final TaskListRepository taskListRepository;
    private final AppUserRepository appUserRepository;

    public TaskDto getById(UUID taskId) {
        return responseMapper.toTaskDto(taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!")));
    }

    public List<TaskDto> getAll(UUID taskListId) {
        return taskRepository.findAllByListOfTasksId(taskListId).stream().map(responseMapper::toTaskDto).toList();
    }

    public TaskDto createTask(RequestTaskDto requestTaskDto) {
        return responseMapper.toTaskDto(taskRepository.save(requestMapper.toTask(requestTaskDto)));
    }

    public TaskDto updateTask(UUID taskId, RequestTaskDto requestTaskDto) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
        return responseMapper.toTaskDto(taskRepository.save(requestMapper.toTask(requestTaskDto, task)));
    }

    public TaskDto migrateTask(UUID taskId, UUID taskListId) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
        task.setListOfTasks(taskListRepository.findById(taskListId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task List not found!")));
        return responseMapper.toTaskDto(taskRepository.save(task));
    }

    public TaskDto assignTask(UUID taskId, Set<UUID> memberIds) {
        var task = taskRepository.findById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found!"));
        task.setAppUsers(appUserRepository.findAppUserByIdIn(memberIds));
        return responseMapper.toTaskDto(taskRepository.save(task));
    }

    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
