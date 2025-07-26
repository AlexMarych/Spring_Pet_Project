package org.spring.pet_project.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.spring.pet_project.Config.ReferenceMapper;
import org.spring.pet_project.Model.*;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, uses = {ReferenceMapper.class})
public interface IdMapper {

    AppUser toAppUser(UUID Id);

    Board toBoard(UUID Id);

    Chat toChat(UUID Id);

    Task toTask(UUID Id);

    TaskList toTaskList(UUID Id);

    Set<AppUser> toAppUserSet(Set<UUID> Ids);

    Set<Task> toTaskSet(Set<UUID> Ids);
}
