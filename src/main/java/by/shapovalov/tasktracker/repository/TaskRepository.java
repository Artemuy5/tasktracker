package by.shapovalov.tasktracker.repository;

import by.shapovalov.tasktracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 19.01.2018
 */

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Modifying
    @Transactional
    @Query("SELECT t FROM Task t WHERE t.project.id = :id")
    List<Task> findAllByProjectId(@Param("id") Long id);
}
