package org.spring.pet_project.Repository;

import org.spring.pet_project.Model.Messages.CommentMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentMessageRepository extends JpaRepository<CommentMessage, UUID> {
    List<CommentMessage> findAllByTaskId(UUID taskId);
}
