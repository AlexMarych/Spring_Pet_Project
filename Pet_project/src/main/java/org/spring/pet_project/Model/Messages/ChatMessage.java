package org.spring.pet_project.Model.Messages;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.spring.pet_project.Model.Chat;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ChatMessage extends Message {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_id", nullable = false, updatable = false)
    private Chat chat;
}
