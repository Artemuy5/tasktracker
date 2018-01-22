package by.shapovalov.tasktracker.service;

import by.shapovalov.tasktracker.dto.CommentDto;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

public interface CommentService {

    void addComment(CommentDto commentDto);

    void editComment(CommentDto commentDto);

    void removeComment(Long id);
}
