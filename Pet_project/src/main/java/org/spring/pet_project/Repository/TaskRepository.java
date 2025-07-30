package org.spring.pet_project.Repository;

import org.spring.pet_project.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByListOfTasksId(UUID taskListId);

    @Query("SELECT t FROM Task t where t.id in :taskIds")
    Set<Task> findAllByIdIn(Set<UUID> taskIds);
}
