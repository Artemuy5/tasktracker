package by.shapovalov.tasktracker.service;

import by.shapovalov.tasktracker.dto.UserDto;
import by.shapovalov.tasktracker.dto.UserFilterDto;
import by.shapovalov.tasktracker.entity.User;
import by.shapovalov.tasktracker.entity.VerificationToken;

import java.util.List;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 11.01.2018
 */

public interface UserService {
    User registerNewUserAccount(UserDto accountDto);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    List<User> search(UserFilterDto userFilterDto);
}
