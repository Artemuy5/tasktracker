package by.shapovalov.tasktracker.service.Impl;

import by.shapovalov.tasktracker.dto.TaskDto;
import by.shapovalov.tasktracker.entity.Task;
import by.shapovalov.tasktracker.repository.ProjectRepository;
import by.shapovalov.tasktracker.repository.StatusRepository;
import by.shapovalov.tasktracker.repository.TaskRepository;
import by.shapovalov.tasktracker.repository.UserRepository;
import by.shapovalov.tasktracker.service.TaskService;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;
    private StatusRepository statusRepository;

    public TaskServiceImpl(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository, StatusRepository statusRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void addTask(TaskDto taskDto) throws IOException {
        Task task = new Task();
        task.setTaskName(taskDto.getTaskName());
        task.setText(taskDto.getText());
        task.setProject(projectRepository.findOne(taskDto .getProjectId()));

        if (taskDto.getUserId() != null)
            task.setUser(userRepository.getOne(taskDto.getUserId()));

        if (taskDto.getStatusId() == null)
            task.setStatus(statusRepository.getOne(1L));
        else
            task.setStatus(statusRepository.getOne(taskDto.getStatusId()));

        taskRepository.save(task);
    }

    @Override
    public void updateTask(TaskDto taskDto) {
        Task task = taskRepository.getOne(taskDto.getId());

        // TODO: update the tasks
        taskRepository.flush();
    }

    @Override
    public void removeTask(Long id) {
        taskRepository.delete(id);
    }
}
