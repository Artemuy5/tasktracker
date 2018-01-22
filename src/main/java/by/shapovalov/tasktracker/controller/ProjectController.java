package by.shapovalov.tasktracker.controller;

import by.shapovalov.tasktracker.dto.ProjectDto;
import by.shapovalov.tasktracker.entity.Project;
import by.shapovalov.tasktracker.entity.User;
import by.shapovalov.tasktracker.repository.ProjectRepository;
import by.shapovalov.tasktracker.repository.UserRepository;
import by.shapovalov.tasktracker.service.ProjectService;
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
 * @since 13.01.2018
 */

@Controller
@RequestMapping("/project")
public class ProjectController {
    private ProjectService projectService;
    private ProjectRepository projectRepository;
    private UserRepository userRepository;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/main")
    @PreAuthorize("hasAnyRole('MANAGER', 'DEVELOPER')")
    public String mainPage(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
                           @RequestParam(name="sort", defaultValue = "dateAdded") String sort) {

        Page<Project> projects = projectService.listProjects(page, sort);
        model.addAttribute("projects", projects.getContent());
        model.addAttribute("totalPages", projects.getTotalPages());
        model.addAttribute("page", page);
        return "project/main";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/addProject")
    public String projectForm(Model model) {
        model.addAttribute("project", new ProjectDto());
        return "project/addProject";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/addProject")
    public String addProject(@ModelAttribute("project") ProjectDto projectDto) throws IOException {
        projectService.addProject(projectDto);
        return "redirect:/project/main";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/editProject/{id}")
    public String editProject(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectRepository.findOne(id));
        return "project/editProject";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/editProject")
    public String editProject(@ModelAttribute("project") ProjectDto projectDto) throws IOException{
        projectService.updateProject(projectDto);
        return "redirect:/project/main";
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping("/{id}/remove")
    public String removeProject(@PathVariable Long id) {
        projectService.removeProject(id);
        return "redirect:/project/main";
    }

    @ModelAttribute("allUsers")
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
