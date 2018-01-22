package by.shapovalov.tasktracker.dto;

import lombok.Data;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

@Data
public class TaskDto {

    private Long id;

    private Long userId;

    private Long projectId;

    private Long statusId;

    private String taskName;

    private String text;
}
