package org.spring.pet_project.Repository;

import org.spring.pet_project.Model.AppUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserTokenRepository extends JpaRepository<AppUserToken, UUID> {

    void deleteByToken(String token);

    Optional<AppUserToken> findByToken(String token);
}
