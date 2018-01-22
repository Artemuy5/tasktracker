package by.shapovalov.tasktracker.service;

import by.shapovalov.tasktracker.entity.User;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 17.01.2018
 */

public interface SecurityService {
    User getCurrentUser();
}
