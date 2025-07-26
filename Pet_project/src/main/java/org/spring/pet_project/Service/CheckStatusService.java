package org.spring.pet_project.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Controller.DTO.Response.CheckStatusDto;
import org.spring.pet_project.Exception.CheckStatusNotFoundException;
import org.spring.pet_project.Exception.TaskNotFoundException;
import org.spring.pet_project.Mapper.ResponseMapper;
import org.spring.pet_project.Model.CheckStatus;
import org.spring.pet_project.Model.Enumeration.Status;
import org.spring.pet_project.Repository.CheckStatusRepository;
import org.spring.pet_project.Repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckStatusService {

    private final CheckStatusRepository checkStatusRepository;
    private final ResponseMapper responseMapper;
    private final TaskRepository taskRepository;

    public CheckStatusDto getById(UUID checkId) {
        return responseMapper.toCheckStatusDto(checkStatusRepository.findById(checkId).orElseThrow(CheckStatusNotFoundException::new));
    }

    public List<CheckStatusDto> getAll(UUID taskId) {
        return checkStatusRepository.findAllByTaskId(taskId).stream().map(responseMapper::toCheckStatusDto).toList();
    }

    public CheckStatusDto createCheckStatus(UUID taskId) {
        var tmpTask = taskRepository.findById(taskId).orElseThrow(TaskNotFoundException::new);

        return responseMapper.toCheckStatusDto(checkStatusRepository.save(CheckStatus
                .builder()
                .status(null)
                .task(tmpTask)
                .build()
        ));
    }

    public CheckStatusDto updateCheckStatus(UUID taskId, Status status) {
        var tmpCheckStatus = checkStatusRepository.findById(taskId).orElseThrow(CheckStatusNotFoundException::new);
        tmpCheckStatus.setStatus(status);
        return responseMapper.toCheckStatusDto(checkStatusRepository.save(tmpCheckStatus));
    }

    public void deleteCheckStatus(UUID taskId) {
        checkStatusRepository.deleteById(taskId);
    }
}
