package by.shapovalov.tasktracker.service.Impl;

import by.shapovalov.tasktracker.dto.CommentDto;
import by.shapovalov.tasktracker.dto.CommentWSMessage;
import by.shapovalov.tasktracker.entity.Comment;
import by.shapovalov.tasktracker.repository.CommentRepository;
import by.shapovalov.tasktracker.repository.TaskRepository;
import by.shapovalov.tasktracker.service.CommentService;
import by.shapovalov.tasktracker.service.SecurityService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private TaskRepository taskRepository;
    private SecurityService securityService;
    private SimpMessagingTemplate simpMessagingTemplate;

    public CommentServiceImpl(CommentRepository commentRepository, TaskRepository taskRepository, SecurityService securityService, SimpMessagingTemplate simpMessagingTemplate) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
        this.securityService = securityService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void addComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setUser(securityService.getCurrentUser());
        comment.setTask(taskRepository.findOne(commentDto.getTaskId()));
        commentRepository.save(comment);

        CommentWSMessage wsMessage = new CommentWSMessage();
        wsMessage.setTaskName(comment.getTask().getTaskName());
        wsMessage.setUsername(comment.getUser().getUsername());
        simpMessagingTemplate.convertAndSend("/topic/comments", wsMessage);
    }

    @Override
    public void editComment(CommentDto commentDto) {
        Comment comment = commentRepository.getOne(commentDto.getId());

        //TODO: edit comment
        commentRepository.flush();
    }

    @Override
    public void removeComment(Long id) {
        commentRepository.delete(id);
    }
}
