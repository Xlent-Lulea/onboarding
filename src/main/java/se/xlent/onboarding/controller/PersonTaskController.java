import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.service.PersonTaskService;

import java.util.List;


@RestController
@RequestMapping("/api/person-task")
public class PersonTaskController {

    @Autowired
    private PersonTaskService personTaskService;

    // 1. List all tasks assigned to a person
    @GetMapping("/person/{personId}/tasks")
    public ResponseEntity<List<PersonTaskEntity>> getTasksForPerson(@PathVariable Long personId) {
        List<PersonTaskEntity> tasks = personTaskService.findTasksForPerson(personId);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // 2. Mark a specific task as completed for a specific person
    @PutMapping("/complete")
    public ResponseEntity<Void> markTaskAsCompleted(@RequestParam Long personId, @RequestParam Long taskId) {
        personTaskService.markTaskAsCompleted(personId, taskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
