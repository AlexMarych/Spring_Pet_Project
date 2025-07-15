package org.spring.pet_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @Size(min = 2, max = 30)
    private String name;

    @Column(nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "ownerAppUser", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Board> ownedBoards;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    private Set<Message> messages;

    @OneToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    private Set<CheckStatus> checkStatus;

    @ManyToMany(mappedBy = "memberAppUser", fetch = FetchType.LAZY)
    @JoinTable(
            name = "board_member",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "board_id")
    )
    private Set<Board> boards;

    @ManyToMany(mappedBy = "appUser", fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_appuser",
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> tasks;


}
