package org.spring.pet_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskList extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 1, max = 20)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "board_id", nullable = false, updatable = false)
    private Board board;

    @OneToMany(mappedBy = "taskList", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Task> tasks;
}
