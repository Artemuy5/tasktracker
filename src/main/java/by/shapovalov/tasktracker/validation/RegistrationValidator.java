package by.shapovalov.tasktracker.validation;

import by.shapovalov.tasktracker.dto.UserDto;
import by.shapovalov.tasktracker.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 16.01.2018
 */

@Component
public class RegistrationValidator implements Validator {
    private UserRepository userRepository;

    public RegistrationValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDto userDto = UserDto.class.cast(target);

        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            errors.rejectValue("username", "user.exists");
        }

        if (userRepository.findByEmail(userDto.getEmail()) != null) {
            errors.rejectValue("email", "email.exists");
        }

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "different.userForm.password");
        }
    }
}
