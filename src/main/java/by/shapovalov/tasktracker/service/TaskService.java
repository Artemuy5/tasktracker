package by.shapovalov.tasktracker.service;

import by.shapovalov.tasktracker.dto.TaskDto;

import java.io.IOException;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

public interface TaskService {

    void addTask(TaskDto taskDto) throws IOException;

    void removeTask(Long id);

    void updateTask(TaskDto taskDto);
}
