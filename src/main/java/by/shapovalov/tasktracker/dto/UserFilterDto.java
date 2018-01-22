package by.shapovalov.tasktracker.dto;

import lombok.Data;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 21.01.2018
 */

@Data
public class UserFilterDto {
    private String firstName;
    private String lastName;
    private String username;
}
