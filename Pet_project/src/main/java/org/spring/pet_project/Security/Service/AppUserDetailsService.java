package org.spring.pet_project.Security.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Repository.AppUserRepository;
import org.spring.pet_project.Security.Details.AppUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {


    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var tempAppUser = appUserRepository.findAppUserByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "App User not found!"));

        return AppUserDetails
                .builder()
                .id(tempAppUser.getId())
                .username(tempAppUser.getName())
                .email(tempAppUser.getEmail())
                .password(tempAppUser.getPassword())
                .build();
    }
}
