package by.shapovalov.tasktracker.controller;

import by.shapovalov.tasktracker.dto.OnRegistrationCompleteEvent;
import by.shapovalov.tasktracker.dto.UserDto;
import by.shapovalov.tasktracker.entity.User;
import by.shapovalov.tasktracker.entity.VerificationToken;
import by.shapovalov.tasktracker.service.UserService;
import by.shapovalov.tasktracker.validation.RegistrationValidator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Artem Shapovalov
 * @version 0.1
 * @since 12.01.2018
 */

@Controller
public class RegistrationController {
    private ApplicationEventPublisher eventPublisher;
    private UserService userService;
    private RegistrationValidator registrationValidator;

    public RegistrationController(ApplicationEventPublisher eventPublisher, UserService userService, RegistrationValidator registrationValidator) {
        this.eventPublisher = eventPublisher;
        this.userService = userService;
        this.registrationValidator = registrationValidator;
    }

    @InitBinder
    public void addRegistrationValidator(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(registrationValidator);
    }

    @GetMapping("/successRegister")
    public String success() {
        return "registration/successRegister";
    }

    @GetMapping("/badUser")
    public String badUser() {
        return "registration/badUser";
    }

    @GetMapping("/emailError")
    public String emailError() {
        return "registration/emailError";
    }

    @GetMapping("/login")
    public String login() {
        return "registration/login";
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "registration/registration";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "registration/registration";
        }
        User registered = userService.registerNewUserAccount(userDto);
        try {
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), getAppUrl(request)));
        } catch (Exception me) {
            return "redirect:/emailError";
        }
        return "registration/successRegister";
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token) {
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            return "redirect:/badUser";
        }
        User user = userService.getUser(token);
        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return "redirect:/login";
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
