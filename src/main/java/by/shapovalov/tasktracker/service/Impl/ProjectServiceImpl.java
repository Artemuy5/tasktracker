package by.shapovalov.tasktracker.service.Impl;

import by.shapovalov.tasktracker.dto.ProjectDto;
import by.shapovalov.tasktracker.entity.Project;
import by.shapovalov.tasktracker.entity.User;
import by.shapovalov.tasktracker.repository.ProjectRepository;
import by.shapovalov.tasktracker.repository.UserRepository;
import by.shapovalov.tasktracker.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 15.01.2018
 */

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService{
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Page<Project> listProjects(int page, String sort) {
        Pageable pageable = new PageRequest(page - 1, 10, new Sort(sort));
        return projectRepository.findAll(pageable);
    }

    @Override
    public void addProject(ProjectDto projectDto) throws IOException {
        Project project = new Project();
        project.setProjectName(projectDto.getProjectName());
        projectRepository.save(project);

        List<User> users = projectDto.getDevelopers();
        if (users != null) {
            for (User user : users) {
                user.setProject(projectRepository.findByProjectName(projectDto.getProjectName()));
                userRepository.flush();
            }
        }
    }

    @Override
    public void updateProject(ProjectDto projectDto) {
        Project project = projectRepository.findOne(projectDto.getId());

        //TODO: update the projects
        projectRepository.flush();
    }

    @Override
    public void removeProject(Long id) {
        projectRepository.delete(id);
    }
}
