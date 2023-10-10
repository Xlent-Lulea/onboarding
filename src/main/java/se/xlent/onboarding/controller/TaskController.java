package se.xlent.onboarding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.service.TaskService;

import java.util.List;

@RestController
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
        return taskService.getById(id);
    }

    @PostMapping(value = "/tasks", consumes = "application/json", produces = "application/json")
    public TaskEntity create(@RequestBody TaskEntity taskEntity, HttpServletResponse response) {
        return taskService.save(taskEntity);
    }

    @PutMapping(value = "/tasks/{id}", consumes = "application/json", produces = "application/json")
    public TaskEntity update(@PathVariable Long id, @RequestBody TaskEntity taskEntity, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/tasks/" + id).toUriString());
        return taskService.save(taskEntity);
    }

    @DeleteMapping(value = "/tasks/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

