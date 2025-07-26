package org.spring.pet_project.Service;

import lombok.AllArgsConstructor;
import org.spring.pet_project.Controller.DTO.Request.RequestBoardDto;
import org.spring.pet_project.Controller.DTO.Request.RequestTaskDto;
import org.spring.pet_project.Controller.DTO.Response.TaskDto;
import org.spring.pet_project.Exception.TaskNotFoundException;
import org.spring.pet_project.Mapper.IdMapper;
import org.spring.pet_project.Mapper.RequestMapper;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ResponseMapper responseMapper;
    private final RequestMapper requestMapper;
    private final IdMapper idMapper;

    public TaskDto getById(UUID taskId) {
        return responseMapper.toTaskDto(taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new));
    }

    public List<TaskDto> getAll(UUID taskListId) {
        return taskRepository.findAllByListOfTasksId(taskListId).stream().map(responseMapper::toTaskDto).toList();
    }

    public TaskDto createTask(RequestTaskDto requestTaskDto) {
        return responseMapper.toTaskDto(taskRepository.save(requestMapper.toTask(requestTaskDto)));
    }

    public TaskDto updateTask(UUID taskId, RequestTaskDto requestTaskDto) {
        var task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        return responseMapper.toTaskDto(taskRepository.save(requestMapper.toTask(requestTaskDto, task)));
    }

    public TaskDto migrateTask(UUID taskId, UUID taskListId) {
        var task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        task.setListOfTasks(idMapper.toTaskList(taskListId));
        return responseMapper.toTaskDto(taskRepository.save(task));
    }

    public TaskDto assignTask(UUID taskId, Set<UUID> memberIds) {
        var task = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        task.setAppUsers(idMapper.toAppUserSet(memberIds));
        return responseMapper.toTaskDto(taskRepository.save(task));
    }

    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
