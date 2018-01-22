package by.shapovalov.tasktracker.dto;

import by.shapovalov.tasktracker.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 15.01.2018
 */

@Data
public class ProjectDto {

    private Long id;

    private Long userId;

    private String projectName;

    private List<User> developers;
}
