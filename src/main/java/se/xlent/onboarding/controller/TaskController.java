package se.xlent.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskType;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.service.TaskService;
import se.xlent.onboarding.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins ="*")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private PersonService personService;

    @PostMapping(value = "/person/{personId}/tasks", consumes = "application/json", produces = "application/json")
    public TaskEntity createTask(@PathVariable Long personId, @RequestBody TaskEntity taskEntity) {
        PersonEntity personEntity = personService.findPersonById(personId);
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        return taskService.saveUpdateTask(personEntity, taskEntity);
    }

    @PostMapping(value = "/person/{personId}/tasks/{taskId}", consumes = "application/json", produces = "application/json")
    public TaskEntity updateTask(@PathVariable Long personId, @PathVariable Long taskId, @RequestBody TaskEntity taskEntity, HttpServletResponse response) {
        PersonEntity personEntity = personService.findPersonById(personId);
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/persons/" + personId + "/tasks/" + taskId).toUriString());
        return taskService.saveUpdateTask(personEntity, taskEntity);
    }



    @GetMapping(value = "/person/{id}/tasks", produces = "application/json")
    public List<TaskEntity> getPersonTasksById(@PathVariable Long id) {
        PersonEntity personEntity = personService.findPersonById(id);
        return personEntity.getTaskEntities();
    }


    @GetMapping(value = "/person/{personId}/tasks/{taskType}", produces = "application/json")
    public List<Task> getTasksByPersonAndType(@PathVariable Long personId, @PathVariable TaskType taskType) {
        PersonEntity personEntity = personService.findPersonById(personId);
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        List<TaskEntity> taskEntityList = taskService.getTasksByPersonAndTaskType(personEntity, taskType);
        return taskEntityList.stream().map(Task::taskBuilder).collect(Collectors.toList());

    }

 


}

