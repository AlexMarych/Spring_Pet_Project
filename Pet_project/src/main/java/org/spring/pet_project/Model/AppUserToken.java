package org.spring.pet_project.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AppUserToken {
    @Id
    @Column(nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private UUID id;

    @Column(nullable = false, length = 2048)
    private String token;

    @Column(columnDefinition = "TIMESTAMP", nullable = false)
    private Instant expirationDate;
}
