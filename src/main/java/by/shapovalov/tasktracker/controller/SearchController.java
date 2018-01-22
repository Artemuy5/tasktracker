package by.shapovalov.tasktracker.controller;

import by.shapovalov.tasktracker.dto.UserFilterDto;
import by.shapovalov.tasktracker.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 21.01.2018
 */

@Controller
public class SearchController {
    private UserService userService;

    public SearchController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/project/searchform")
    public String search(Model model) {
        model.addAttribute("filter", new UserFilterDto());
        return "project/searchform";
    }

    @PostMapping("/project/searchform")
    public String search(Model model, @ModelAttribute("filter") UserFilterDto filter) {
        model.addAttribute("filter", filter);
        model.addAttribute("users", userService.search(filter));
        return "search";
    }
}
