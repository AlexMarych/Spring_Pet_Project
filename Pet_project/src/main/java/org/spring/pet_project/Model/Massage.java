package org.spring.pet_project.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Massage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    @Size(min = 1, max = 200)
    private String text;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime TimeOfSend;

    // TODO: add attribute for attachments
}
