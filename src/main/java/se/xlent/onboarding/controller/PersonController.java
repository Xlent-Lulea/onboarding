package se.xlent.onboarding.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskType;
import se.xlent.onboarding.model.Person;
import se.xlent.onboarding.service.PersonService;
import se.xlent.onboarding.service.TaskService;
import se.xlent.onboarding.service.PersonServiceImpl;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins ="*")
public class PersonController {


    @Autowired
    private PersonService personService;
    @Autowired
    private TaskService taskService;

    @PostMapping(value = "/createPerson", consumes = "application/json", produces = "application/json")
    public PersonEntity createPerson(@RequestBody PersonEntity personEntity) throws JsonProcessingException {
        //Standard tasks created when a person is created
        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setTitle("1TitleBeforeStart");
        taskEntity1.setDescription("1DescriptionBeforeStart");
        taskEntity1.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity1.setTaskType(TaskType.BEFORE_START);
        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity2.setTitle("2TitleAfterStartBuddy");
        taskEntity2.setDescription("2DescriptionAfterStartBuddy");
        taskEntity2.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity2.setTaskType(TaskType.AFTER_START_BUDDY);
        TaskEntity taskEntity3 = new TaskEntity();
        taskEntity3.setTitle("3TitleAfterStartRecruit");
        taskEntity3.setDescription("3AfterStartRecruit");
        taskEntity3.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity3.setTaskType(TaskType.AFTER_START_RECRUIT);
        TaskEntity taskEntity4 = new TaskEntity();
        taskEntity4.setTitle("4TitleAfterStartManager");
        taskEntity4.setDescription("4DescriptionAfterStartManager");
        taskEntity4.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity4.setTaskType(TaskType.BLOMBLAD_1);
        TaskEntity taskEntity5 = new TaskEntity();
        taskEntity5.setTitle("5TitleAfterStartManager");
        taskEntity5.setDescription("5DescriptionAfterStartManager");
        taskEntity5.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity5.setTaskType(TaskType.BLOMBLAD_2);
        TaskEntity taskEntity6 = new TaskEntity();
        taskEntity6.setTitle("6TitleAfterStartManager");
        taskEntity6.setDescription("6DescriptionAfterStartManager");
        taskEntity6.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity6.setTaskType(TaskType.BLOMBLAD_3);
        TaskEntity taskEntity7 = new TaskEntity();
        taskEntity7.setTitle("7TitleAfterStartManager");
        taskEntity7.setDescription("7DescriptionAfterStartManager");
        taskEntity7.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity7.setTaskType(TaskType.BLOMBLAD_4);
        TaskEntity taskEntity8 = new TaskEntity();
        taskEntity8.setTitle("8TitleAfterStartManager");
        taskEntity8.setDescription("8DescriptionAfterStartManager");
        taskEntity8.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity8.setTaskType(TaskType.BLOMBLAD_5);
        TaskEntity taskEntity9 = new TaskEntity();
        taskEntity9.setTitle("9TitleAfterStartManager");
        taskEntity9.setDescription("9DescriptionAfterStartManager");
        taskEntity9.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity9.setTaskType(TaskType.BLOMBLAD_6);
        TaskEntity taskEntity10 = new TaskEntity();
        taskEntity10.setTitle("10TitleAfterStartManager");
        taskEntity10.setDescription("10DescriptionAfterStartManager");
        taskEntity10.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity10.setTaskType(TaskType.BLOMBLAD_7);
        TaskEntity taskEntity11 = new TaskEntity();
        taskEntity11.setTitle("11TitleAfterStartManager");
        taskEntity11.setDescription("11DescriptionAfterStartManager");
        taskEntity11.setUrl("https://www.youtube.com/watch?v=QH2-TGUlwu4");
        taskEntity11.setTaskType(TaskType.BLOMBLAD_8);
        personEntity.addTasks(List.of(taskEntity1, taskEntity2, taskEntity3, taskEntity4, taskEntity5, taskEntity6, taskEntity7, taskEntity8, taskEntity9, taskEntity10, taskEntity11));
        personEntity.setActive(true);
        PersonEntity savedPersonEntity = personService.saveUpdatePerson(personEntity);
        return savedPersonEntity;
    }


    @PostMapping(value = "/updatePerson", consumes = "application/json", produces = "application/json")
    public PersonEntity updatePerson(@RequestBody PersonEntity personEntity, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/findPerson/" + personEntity.getId()).toUriString());


        return personService.saveUpdatePerson(personEntity);
    }

    @GetMapping(value = "/activePersons", produces = "application/json")
    public List<Person> getActivePersons() {
        List<PersonEntity> activePersons = personService.getActivePersons();
        return activePersons.stream().map(Person::persons).collect(Collectors.toList());
    }

    @GetMapping(value = "/persons", produces = "application/json")
    public List<Person> getAllPersons() {
        List<PersonEntity> allPersons = personService.getAllPersons();
        return allPersons.stream().map(Person::persons).collect(Collectors.toList());
    }

    @GetMapping(value = "/inactivePersons", produces = "application/json")
    public List<Person> getInactivePersons() {
        List<PersonEntity> inactivePersons = personService.getInactivePersons();
        return inactivePersons.stream().map(Person::persons).collect(Collectors.toList());
    }

    @GetMapping(value = "/person/{id}", produces = "application/json")
    public PersonEntity getPersonById(@PathVariable Long id) {

        return personService.findPersonById(id);

    }


    @PutMapping(value = "/person/{id}/deactivate", produces = "application/json")
    public PersonEntity deactivatePerson(@PathVariable Long id) {
        PersonEntity personEntity = personService.findPersonById(id);
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        personEntity.setActive(false);
        return personService.saveUpdatePerson(personEntity);
    }

    @PutMapping(value = "/person/{id}/activate", produces = "application/json")
    public PersonEntity activatePerson(@PathVariable Long id) {
        PersonEntity personEntity = personService.findPersonById(id);
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        personEntity.setActive(true);
        return personService.saveUpdatePerson(personEntity);
    }

    @DeleteMapping(value = "/person/{id}", produces = "application/json")
    public void deletePerson(@PathVariable Long id) {
        PersonEntity personEntity = personService.findPersonById(id);
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        personService.deletePerson(personEntity);
    }


}
