package by.shapovalov.tasktracker.repository;

import by.shapovalov.tasktracker.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 15.01.2018
 */

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByProjectName(String projectName);
}
