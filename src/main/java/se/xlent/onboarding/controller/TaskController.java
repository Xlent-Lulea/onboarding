package se.xlent.onboarding.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.webjars.NotFoundException;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.service.TaskService;

import java.util.List;

@RestController
@Validated
@CrossOrigin(origins ="*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/tasks", produces = "application/json")
    public  List<TaskEntity> getAll() {
        return taskService.getAll();
    }

    @GetMapping(value = "/tasks/{id}", produces = "application/json")
    public TaskEntity getById(@PathVariable Long id) {
        TaskEntity task = taskService.getById(id);

        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id " + id + " not found");
        }

        return task;
    }

    @PostMapping(value = "/tasks", consumes = "application/json", produces = "application/json")
    public TaskEntity create(@Valid @RequestBody TaskEntity task, HttpServletResponse response) {
        return taskService.create(task);
    }

    @PutMapping(value = "/tasks/{id}", consumes = "application/json", produces = "application/json")
    public TaskEntity update(@PathVariable Long id, @Valid @RequestBody TaskEntity task, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/tasks/" + id).toUriString());
        return taskService.save(task);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            taskService.delete(id);
        } catch (NotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        return ResponseEntity.noContent().build();
    }
}

