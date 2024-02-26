package se.xlent.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.xlent.onboarding.model.PersonTask;
import se.xlent.onboarding.service.PersonTaskService;

import java.util.List;


@RestController
@Validated
@CrossOrigin(origins ="*")
public class PersonTaskController {
    @Autowired
    private PersonTaskService personTaskService;

    @GetMapping("/person/{personId}/tasks")
    public List<PersonTask> getAllByPersonId(@PathVariable Long personId) {
            return personTaskService.getAllByPersonId(personId);
    }

     @GetMapping(value = "/person/{personId}/tasks/{taskId}", produces = "application/json")
     public PersonTask getById(@PathVariable Long taskId) {
        return personTaskService.getById(taskId);
     }

    @PutMapping(value = "/person/{personId}/tasks/{taskId}/toggle-completed", produces = "application/json")
    public PersonTask toggleTaskCompletionStatus(@PathVariable Long taskId) {
        return personTaskService.toggleTaskCompletionStatus(taskId);
    }
}
