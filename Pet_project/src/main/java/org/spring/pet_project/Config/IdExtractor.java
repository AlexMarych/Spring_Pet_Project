package org.spring.pet_project.Config;

import org.spring.pet_project.Model.BaseEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class IdExtractor {

    public UUID extractId(BaseEntity entity) {
        return entity.getId();
    }

    public Set<UUID> extractIds(Set<? extends BaseEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }
}
