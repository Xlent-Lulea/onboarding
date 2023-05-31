package se.xlent.onboarding.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskType;
import se.xlent.onboarding.model.Person;
import se.xlent.onboarding.service.PersonService;
import se.xlent.onboarding.service.TaskService;

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
        //Skapa upp 1 task i vardera kategori för personen
        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.setTitle("1TitleBeforeStart");
        taskEntity1.setDescription("1DescriptionBeforeStart");
        taskEntity1.setTaskType(TaskType.BEFORE_START);
        TaskEntity taskEntity2 = new TaskEntity();
        taskEntity2.setTitle("2TitleAfterStartBuddy");
        taskEntity2.setDescription("2DescriptionAfterStartBuddy");
        taskEntity2.setTaskType(TaskType.AFTER_START_BUDDY);
        TaskEntity taskEntity3 = new TaskEntity();
        taskEntity3.setTitle("3TitleAfterStartRecruit");
        taskEntity3.setDescription("3AfterStartRecruit");
        taskEntity3.setTaskType(TaskType.AFTER_START_RECRUIT);

        personEntity.addTasks(List.of(taskEntity1, taskEntity2, taskEntity3));
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




}
