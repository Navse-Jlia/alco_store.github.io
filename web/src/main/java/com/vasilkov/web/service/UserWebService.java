package com.vasilkov.web.service;


import com.vasilkov.model.dto.UserDto;
import com.vasilkov.model.enums.UserRole;
import com.vasilkov.web.dto.UserSecurityDto;
import com.vasilkov.web.rabbit.producer.UserProducer;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The type User web service.
 *
 * @author Artem Vasilkov
 */
@AllArgsConstructor
@Service
public class UserWebService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserProducer producer;

    /**
     * Load user by username user details.
     *
     * @param email the email
     * @return the user details
     * @throws UsernameNotFoundException the username not found exception
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = producer.getByEmail(email);
        UserSecurityDto webUser = UserSecurityDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .role(user.getRole())
                .build();
        return webUser;
    }

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        if (producer.getByEmail("admin@gmail.com") != null) {
            return;
        }
        UserDto user = UserDto.builder()
                .firstName("admin")
                .lastName("admin")
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin123"))
                .role(UserRole.ROLE_ADMIN)
                .build();
        producer.save(user);
    }
}