package se.xlent.onboarding.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.xlent.onboarding.service.MailService;

@RestController
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/sendmail")
    public String sendMail() {
        mailService.sendSimpleMessage(
                "c6f69174.xlent.onmicrosoft.com@emea.teams.ms",
                "Hej XLU!",
                "testmail från Marcus. Ser någon detta? Joddla högljutt på kontoret om du sett det!"
        );
        return "Email sent!";
    }

    @GetMapping("/sendteamsmail")
    public String sendEmailToTeams() {
        String teamsEmail = "8fc379d5.xlent.onmicrosoft.com@emea.teams.ms"; //Replace with the correctemail later
        mailService.sendSimpleMessage(teamsEmail, "Hello Tekniklabbet", "Testmail från SpringBootApplication using google smtpservah.");
        return "Teams email sent!";
    }
}
