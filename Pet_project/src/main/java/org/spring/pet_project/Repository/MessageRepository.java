package org.spring.pet_project.Repository;

import org.spring.pet_project.Model.Messages.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository<T extends Message> extends JpaRepository<T, UUID> {

    List<T> findAllByChatId(UUID chatId);

    List<T> findAllByTaskId(UUID taskId);


}
