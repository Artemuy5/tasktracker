package by.shapovalov.tasktracker.dto;

import by.shapovalov.tasktracker.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 13.01.2018
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private String appUrl;

    private Locale locale;

    private User user;

    public OnRegistrationCompleteEvent(User user, Locale locale, String appUrl) {
        super(user);
        this.user = user;
        this.locale = locale;
        this.appUrl = appUrl;
    }
}
