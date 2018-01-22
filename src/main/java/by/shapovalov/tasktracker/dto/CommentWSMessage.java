package by.shapovalov.tasktracker.dto;

import lombok.Data;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

@Data
public class CommentWSMessage {
    private String taskName;
    private String username;
}
