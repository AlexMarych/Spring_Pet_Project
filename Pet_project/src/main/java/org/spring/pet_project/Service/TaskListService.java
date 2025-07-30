package org.spring.pet_project.Service;

import lombok.AllArgsConstructor;
import org.spring.pet_project.Controller.DTO.Response.TaskListDto;
import org.spring.pet_project.Exception.BoardNotFoundException;
import org.spring.pet_project.Exception.TaskListNotFoundException;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Model.TaskList;
import org.spring.pet_project.Repository.BoardRepository;
import org.spring.pet_project.Repository.TaskListRepository;
import org.spring.pet_project.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final ResponseMapper responseMapper;
    private final BoardRepository boardRepository;
    private final TaskRepository taskRepository;

    public List<TaskListDto> getAll(UUID boardId) {
         return taskListRepository.findAllByBoardId(boardId).stream().map(responseMapper::toTaskListDto).toList();
    }


    public TaskListDto createTaskList(UUID boardId, String title) {
        var tmpBoard = boardRepository.findById(boardId).orElseThrow(BoardNotFoundException::new);

        return responseMapper.toTaskListDto(TaskList
                .builder()
                .board(tmpBoard)
                .title(title)
                .build()
        );
    }

    public TaskListDto updateTaskList(UUID taskListId, String title) {
        var tmpTaskList = taskListRepository.findById(taskListId).orElseThrow(TaskListNotFoundException::new);
        tmpTaskList.setTitle(title);
        return responseMapper.toTaskListDto(taskListRepository.save(tmpTaskList));
    }

    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }

    public TaskListDto updateTasks(UUID taskListId, Set<UUID> taskIds) {
        var tmpTaskList = taskListRepository.findById(taskListId).orElseThrow(TaskListNotFoundException::new);
        tmpTaskList.setTasks(taskRepository.findAllByIdIn(taskIds));
        return responseMapper.toTaskListDto(taskListRepository.save(tmpTaskList));
    }
}
