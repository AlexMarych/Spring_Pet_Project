package org.spring.pet_project.Security.Service;

import lombok.RequiredArgsConstructor;
import org.spring.pet_project.Exception.AppUserNotFoundException;
import org.spring.pet_project.Repository.AppUserRepository;
import org.spring.pet_project.Security.Details.AppUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {


    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var tempAppUser = appUserRepository.findAppUserByEmail(email).orElseThrow(AppUserNotFoundException::new);

        return AppUserDetails
                .builder()
                .id(tempAppUser.getId())
                .username(tempAppUser.getName())
                .email(tempAppUser.getEmail())
                .password(tempAppUser.getPassword())
                .build();
    }
}
