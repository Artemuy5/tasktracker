package by.shapovalov.tasktracker.repository;

import by.shapovalov.tasktracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 11.01.2018
 */

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);

    @Modifying
    @Transactional
    @Query("SELECT u FROM User u WHERE u.role = 1 AND u.project IS NULL")
    List<User> findAll();

    @Modifying
    @Transactional
    @Query("SELECT u FROM User u WHERE u.project.id = :id")
    List<User> findAllByProjectId(@Param("id") Long id);

    List<User> findAllByFirstNameContainsOrLastNameContainsOrUsernameContains(String firstName, String lastName, String username);
}
