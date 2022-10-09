package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;
import java.util.ArrayList;


@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {

        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your tasks");
        functionality.add("Provides connection with Trello Account");
        functionality.add("Application allows sending tasks to Trello");

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://mzebik.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("GOODBAY_MESSAGE", "Best regards, Administration Team");
        context.setVariable("Company",
                "Company: " + adminConfig.getCompanyName() + "\n" +
                        "Company goal: " + adminConfig.getCompanyGoal() + "\n" +
                        "E-Mail adress: " + adminConfig.getCompanyEmail() + "\n" +
                        "Telephone No.: " + adminConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildInformationEmail(String message) {

        Context context = new Context();
        context.setVariable("message", message);context.setVariable("GOODBAY_MESSAGE", "Best regards, Administration Team");
        context.setVariable("Company",
                "Company: " + adminConfig.getCompanyName() + "\n" +
                        "Company goal: " + adminConfig.getCompanyGoal() + "\n" +
                        "E-Mail adress: " + adminConfig.getCompanyEmail() + "\n" +
                        "Telephone No.: " + adminConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("admin_config", adminConfig);

        return templateEngine.process("mail/information-mail", context);
    }
}

