package org.spring.pet_project.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.spring.pet_project.Model.Enumeration.Status;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CheckStatus {
    @Id
    private UUID id;

    private LocalDateTime timeOfCheck;
    private Status status;
}
