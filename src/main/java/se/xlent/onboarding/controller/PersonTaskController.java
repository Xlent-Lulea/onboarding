package se.xlent.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import org.webjars.NotFoundException;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.service.PersonTaskService;

import java.util.List;


@RestController
@CrossOrigin(origins ="*")
public class PersonTaskController {
    @Autowired
    private PersonTaskService personTaskService;

    @GetMapping("/person/{personId}/tasks")
    public List<PersonTaskEntity> getAllByPersonId(@PathVariable Long personId) {
        List<PersonTaskEntity> tasks;

        try {
            tasks = personTaskService.getAllByPersonId(personId);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return tasks;
    }

     @GetMapping(value = "/person/{personId}/tasks/{taskId}", produces = "application/json")
     public PersonTaskEntity getById(@PathVariable Long taskId) {
        return personTaskService.getById(taskId);
     }

    @PutMapping(value = "/person/{personId}/tasks/{taskId}/toggle-completed", produces = "application/json")
    public PersonTaskEntity toggleTaskCompletionStatus(@PathVariable Long taskId) {
        return personTaskService.toggleTaskCompletionStatus(taskId);
    }
}
