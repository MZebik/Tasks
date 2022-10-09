package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class MailCreatorService {

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;
    @Autowired
    private AdminConfig adminConfig;

    public String buildTrelloCardEmail(String message) {
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
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

}

