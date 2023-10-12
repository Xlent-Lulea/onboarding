package se.xlent.onboarding.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.service.TaskTypeService;

import java.util.List;

@RestController
@CrossOrigin(origins ="*")
public class TaskTypeController {

    @Autowired
    private TaskTypeService taskTypeService;

    @GetMapping(value = "/taskTypes", produces = "application/json")
    public  List<TaskTypeEntity> getAll() {
        return taskTypeService.getAll();
    }

    @GetMapping(value = "/taskTypes/{id}", produces = "application/json")
    public TaskTypeEntity getById(@PathVariable Long id) {
        return taskTypeService.getById(id);
    }

    @PostMapping(value = "/taskTypes", consumes = "application/json", produces = "application/json")
    public TaskTypeEntity create(@RequestBody TaskTypeEntity taskTypeEntity, HttpServletResponse response) {
        return taskTypeService.save(taskTypeEntity);
    }

    @PutMapping(value = "/taskTypes/{id}", consumes = "application/json", produces = "application/json")
    public TaskTypeEntity update(@PathVariable Long id, @RequestBody TaskTypeEntity taskTypeEntity, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/tasks/" + id).toUriString());
        return taskTypeService.save(taskTypeEntity);
    }

    @DeleteMapping(value = "/taskTypes/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

