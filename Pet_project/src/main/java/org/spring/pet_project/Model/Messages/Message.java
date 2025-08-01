package org.spring.pet_project.Model.Messages;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.spring.pet_project.Model.AppUser;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public abstract class Message   {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @Size(min = 1, max = 200)
    private String text;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime timeOfSend;

    // TODO: add attribute for attachments

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private AppUser appUser;
}
