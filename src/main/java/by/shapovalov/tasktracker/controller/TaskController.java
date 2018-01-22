package by.shapovalov.tasktracker.controller;

import by.shapovalov.tasktracker.dto.TaskDto;
import by.shapovalov.tasktracker.entity.Project;
import by.shapovalov.tasktracker.entity.Status;
import by.shapovalov.tasktracker.entity.Task;
import by.shapovalov.tasktracker.repository.StatusRepository;
import by.shapovalov.tasktracker.repository.TaskRepository;
import by.shapovalov.tasktracker.repository.UserRepository;
import by.shapovalov.tasktracker.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

@Controller
@RequestMapping("/task")
public class TaskController {
    private TaskService taskService;
    private TaskRepository taskRepository;
    private StatusRepository statusRepository;
    private UserRepository userRepository;

    public TaskController(TaskService taskService, TaskRepository taskRepository, StatusRepository statusRepository, UserRepository userRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/tasks/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'DEVELOPER')")
    public String showTasks(@PathVariable Long id, Model model) {
        model.addAttribute("tasks", taskRepository.findAllByProjectId(id));
        return "task/tasks";
    }

    @GetMapping("/task/{id}")
    @PreAuthorize("hasAnyRole('MANAGER', 'DEVELOPER')")
    public String showTaskDescription(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskRepository.findOne(id));
        return "task/task";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/addTask/{id}")
    public String addTask(@PathVariable Long id, Model model) {
        model.addAttribute("projectId", id);
        model.addAttribute("allDevelopers", userRepository.findAllByProjectId(id));
        model.addAttribute("task", new TaskDto());
        return "task/addTask";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/addTask")
    public String addTask(@ModelAttribute("task") TaskDto taskDto) throws IOException {
        taskService.addTask(taskDto);
        return "redirect:/project/main";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}/remove")
    public String removeTask(@PathVariable Long id) {
        taskService.removeTask(id);
        return "redirect:/project/main";
    }

    @ModelAttribute("allStatuses")
    public List<Status> allStatuses() {
        return statusRepository.findAll();
    }
}
