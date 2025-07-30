package org.spring.pet_project.Model.Messages;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.spring.pet_project.Model.Task;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CommentMessage extends Message {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false, updatable = false)
    private Task task;
}
