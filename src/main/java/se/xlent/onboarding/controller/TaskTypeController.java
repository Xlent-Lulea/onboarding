package se.xlent.onboarding.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.xlent.onboarding.model.TaskType;
import se.xlent.onboarding.service.TaskTypeService;

import java.util.List;

@RestController
@Validated
@CrossOrigin(origins ="*")
public class TaskTypeController {

    @Autowired
    private TaskTypeService taskTypeService;

    @GetMapping(value = "/taskTypes", produces = "application/json")
    public  List<TaskType> getAll() {
        return taskTypeService.getAll();
    }

    @GetMapping(value = "/taskTypes/{id}", produces = "application/json")
    public TaskType getById(@PathVariable Long id) {
        TaskType taskType = taskTypeService.getById(id);

        if (taskType == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskType with id " + id + " not found");
        }

        return taskType;
    }

    @PostMapping(value = "/taskTypes", consumes = "application/json", produces = "application/json")
    public TaskType create(@Valid @RequestBody TaskType taskType, HttpServletResponse response) {
        return taskTypeService.save(taskType);
    }

    @PutMapping(value = "/taskTypes/{id}", consumes = "application/json", produces = "application/json")
    public TaskType update(@PathVariable Long id, @Valid @RequestBody TaskType taskType, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/tasks/" + id).toUriString());
        return taskTypeService.save(taskType);
    }

    @DeleteMapping(value = "/taskTypes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

