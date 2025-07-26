package org.spring.pet_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.spring.pet_project.Model.Messages.CommentMessage;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String name;

    @Size(max = 500)
    private String description;

    private LocalDateTime dueDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "taskList_id", nullable = false, updatable = false)
    private TaskList listOfTasks;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<CheckStatus> checkStatuses;

    @OneToMany(mappedBy = "task", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<CommentMessage> messages;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<AppUser> appUsers;

}
