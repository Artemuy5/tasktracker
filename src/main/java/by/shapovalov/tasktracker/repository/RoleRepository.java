package by.shapovalov.tasktracker.repository;

import by.shapovalov.tasktracker.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 11.01.2018
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
