package org.spring.pet_project.Config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.NonNull;
import org.mapstruct.ObjectFactory;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ReferenceMapper {
    @PersistenceContext
    private EntityManager entityManager;

    @ObjectFactory
    public <T> T map(@NonNull final UUID id, @TargetType Class<T> type) {
        return entityManager.getReference(type, id);
    }

    public <T> Set<T> mapSet(@NonNull Set<UUID> ids, @TargetType Class<T> type) {
        return ids.stream()
                .map(id -> entityManager.getReference(type, id))
                .collect(Collectors.toSet());
    }
}
