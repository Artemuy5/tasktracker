package by.shapovalov.tasktracker.service.Impl;

import by.shapovalov.tasktracker.entity.User;
import by.shapovalov.tasktracker.repository.UserRepository;
import by.shapovalov.tasktracker.service.SecurityService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 17.01.2018
 */

@Service
public class SecurityServiceImpl implements SecurityService {
    private UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() {
        UserDetails userDetails = UserDetails.class.cast(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return userRepository.findByUsername(userDetails.getUsername());
    }
}
