package org.spring.pet_project.Repository;

import org.spring.pet_project.Model.Messages.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, UUID> {
    List<ChatMessage> findAllByChatId(UUID chatId);
}
