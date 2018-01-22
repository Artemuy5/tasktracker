package by.shapovalov.tasktracker.service.Impl;

import by.shapovalov.tasktracker.entity.User;
import by.shapovalov.tasktracker.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 11.01.2018
 */

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + username);
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword().toLowerCase(),
                    user.isEnabled(),
                    true,
                    true,
                    true,
                    Collections.singletonList(user.getRole()).stream().map(
                            role -> new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toSet()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
