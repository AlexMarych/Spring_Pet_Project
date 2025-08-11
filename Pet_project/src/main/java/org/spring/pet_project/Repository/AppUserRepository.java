package org.spring.pet_project.Repository;

import jakarta.validation.constraints.Email;
import org.spring.pet_project.Model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    Set<AppUser> findAppUserByIdIn(Set<UUID> ids);

    List<AppUser> findAppUsersByBoardsId(UUID boardId);

    Optional<AppUser> findAppUserByEmail(@Email String email);
}
