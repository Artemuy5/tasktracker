package by.shapovalov.tasktracker.service.Impl;

import by.shapovalov.tasktracker.dto.UserDto;
import by.shapovalov.tasktracker.dto.UserFilterDto;
import by.shapovalov.tasktracker.entity.User;
import by.shapovalov.tasktracker.entity.VerificationToken;
import by.shapovalov.tasktracker.repository.RoleRepository;
import by.shapovalov.tasktracker.repository.UserRepository;
import by.shapovalov.tasktracker.repository.VerificationTokenRepository;
import by.shapovalov.tasktracker.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 11.01.2018
 */

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private VerificationTokenRepository tokenRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, VerificationTokenRepository tokenRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User registerNewUserAccount(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRole(roleRepository.getOne(1L));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String verificationToken) {
        return tokenRepository.findByToken(verificationToken).getUser();
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public List<User> search(UserFilterDto filter) {
        return userRepository.findAllByFirstNameContainsOrLastNameContainsOrUsernameContains(filter.getFirstName(),
                filter.getLastName(), filter.getUsername());
    }
}
