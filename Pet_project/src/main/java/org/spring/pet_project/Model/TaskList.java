package org.spring.pet_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @Size(min = 1, max = 20)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY , optional = false)
    @JoinColumn(name = "board_id", nullable = false, updatable = false)
    private Board board;

    @OneToMany(mappedBy = "listOfTasks", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Task> tasks;
}
