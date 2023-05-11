package se.xlent.onboarding.controller;

import org.springframework.web.bind.annotation.*;
import se.xlent.onboarding.model.Step;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.model.TaskType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/api/tasks")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAllTasks() {
        try {
            List<Task> tasks = getMockedTasks();
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (MalformedURLException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{taskType}")
    public ResponseEntity getTasksByType(@PathVariable("taskType") TaskType taskType) {
        try {
            List<Task> tasks = getMockedTasks().stream()
                    .filter(t -> t.getTaskType().equals(taskType))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } catch (MalformedURLException ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getMessage());
        }
    }

    private List<Task> getMockedTasks() throws MalformedURLException {

        List<Task> t = new ArrayList<>();
        Task task1 = new Task(1L, TaskType.BEFORE_START, "Cinode", "Skicka mail enligt mall till den nyanställde.", new URL("https://www.dustin.se/"), false);
        task1.setSteps(new ArrayList<>());
        task1.getSteps().add(new Step(1L, TaskType.AFTER_START_RECRUIT, "Lägga upp CV", "Lägga upp CV och uppdatera i Cinode – lösen 1a inloggning: xlent123  (när man börjat)", new URL("https://app.cinode.com/start"), false));
        task1.getSteps().add(new Step(1L, TaskType.AFTER_START_RECRUIT, "Lägga upp CV2", "Lägga upp CV2 och uppdatera i Cinode – lösen 1a inloggning: xlent123  (när man börj2at)", new URL("https://app.cinode.com/start"), false));
        t.add(task1);

        t.add(new Task(1L, TaskType.BEFORE_START, "Beställ blommor", "Blommor beställs från Interflora.", new URL("https://www.interflora.se/"), false));

        t.add(new Task(1L, TaskType.AFTER_START_BUDDY, "Gå igenom onboardinglista", "Visa onboardinglistan till den nyanställde.", null, false));
        t.add(new Task(1L, TaskType.AFTER_START_BUDDY, "Bjud på lunch", "Bjud den nyanställde på lunch första arbetsdagen.", null, false));
        t.add(new Task(1L, TaskType.AFTER_START_RECRUIT, "Installera dator", "Bjud den nyanställde på lunch första arbetsdagen.", null, false));
        t.add(new Task(1L, TaskType.AFTER_START_RECRUIT, "Lägg in CV i Cinode", "Skapa CV i Cinode och ladda upp bild.", new URL("https://app.cinode.com/xlent/login"), false));
        t.add(new Task(1L, TaskType.AFTER_START_RECRUIT, "Gör inlägg Workplace", "Presentera dig själv i gruppen \"Jag är ny på XLENT\".", new URL("https://xlent.workplace.com/"), false));
        return t;
    }
}