package org.spring.pet_project.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.pet_project.Model.Enumeration.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CheckStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @UpdateTimestamp
    private LocalDateTime timeOfCheck;

    private Status status;
}
