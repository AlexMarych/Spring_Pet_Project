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
public class Board extends BaseEntity {

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String title;

    @Size(max = 100)
    private String description;

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<TaskList> listsOfTasks;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Chat> chats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id", nullable = false, updatable = false)
    private AppUser ownerAppUser;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<AppUser> memberAppUsers;
}
