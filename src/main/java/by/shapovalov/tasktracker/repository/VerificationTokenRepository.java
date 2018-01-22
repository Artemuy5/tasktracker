package by.shapovalov.tasktracker.repository;

import by.shapovalov.tasktracker.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 13.01.2018
 */

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
