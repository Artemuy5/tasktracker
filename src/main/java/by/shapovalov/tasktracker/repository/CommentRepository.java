package by.shapovalov.tasktracker.repository;

import by.shapovalov.tasktracker.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
