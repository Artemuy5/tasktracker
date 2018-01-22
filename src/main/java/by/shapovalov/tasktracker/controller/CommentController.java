package by.shapovalov.tasktracker.controller;

import by.shapovalov.tasktracker.dto.CommentDto;
import by.shapovalov.tasktracker.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

@Controller
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void add(@RequestBody CommentDto commentDto) {
        commentService.addComment(commentDto);
    }

    @GetMapping("/{id}/remove")
    public String removeComment(@PathVariable Long id) {
        commentService.removeComment(id);
        return "redirect:/task/task/{id}";
    }
}
