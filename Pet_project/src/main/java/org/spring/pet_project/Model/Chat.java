package org.spring.pet_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.spring.pet_project.Model.Messages.ChatMessage;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @Size(min = 2,max = 30)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", updatable = false)
    private Board board;

    @OneToMany(mappedBy = "chat", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<ChatMessage> messages;

}
