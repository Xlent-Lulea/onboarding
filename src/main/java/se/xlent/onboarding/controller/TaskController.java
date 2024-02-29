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
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.service.TaskService;

import java.util.List;

@RestController
@Validated
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/tasks", produces = "application/json")
    public  List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping(value = "/tasks/{id}", produces = "application/json")
    public Task getById(@PathVariable Long id) {
        Task task = taskService.getById(id);

        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id " + id + " not found");
        }

        return task;
    }

    @PostMapping(value = "/tasks", consumes = "application/json", produces = "application/json")
    public Task create(@Valid @RequestBody Task task, HttpServletResponse response) {
        return taskService.create(task);
    }

    @PutMapping(value = "/tasks/{id}", consumes = "application/json", produces = "application/json")
    public Task update(@PathVariable Long id, @Valid @RequestBody Task task, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/tasks/" + id).toUriString());
        return taskService.save(task);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
            taskService.delete(id);

        return ResponseEntity.noContent().build();
    }
}

