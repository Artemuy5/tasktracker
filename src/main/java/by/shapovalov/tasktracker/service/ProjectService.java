package by.shapovalov.tasktracker.service;

import by.shapovalov.tasktracker.dto.ProjectDto;
import by.shapovalov.tasktracker.entity.Project;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 15.01.2018
 */

public interface ProjectService {
    Page<Project> listProjects(int page, String sort);

    void addProject(ProjectDto projectDto) throws IOException;

    void updateProject(ProjectDto projectDto);

    void removeProject(Long id);
}
