package by.shapovalov.tasktracker.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 11.01.2018
 */

@Data
public class UserDto {

    @NotNull
    @Size(min = 2, max = 16)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 16)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 16)
    private String username;

    @NotNull
    @Size(min = 6, max = 16)
    private String password;

    private String confirmPassword;

    @NotNull
    @Email
    private String email;
}
