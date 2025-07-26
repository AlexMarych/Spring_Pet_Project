package org.spring.pet_project.Repository;

import org.spring.pet_project.Model.CheckStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CheckStatusRepository extends JpaRepository<CheckStatus, UUID> {

    List<CheckStatus> findAllByTaskId(UUID taskId);
}
