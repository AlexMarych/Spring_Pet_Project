package org.spring.pet_project.Model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.pet_project.Model.Enumeration.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @UpdateTimestamp
    private LocalDateTime timeOfCheck;

    private Status status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false, updatable = false)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

}
