package org.spring.pet_project.Repository;

import org.spring.pet_project.Model.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {

    List<TaskList> findAllByBoardId(UUID boardId);

    Set<TaskList> findAllByIdIn(Set<UUID> taskListsIds);
}
