package se.xlent.onboarding.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.model.Person;
import se.xlent.onboarding.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins ="*")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons", produces = "application/json")
    public List<Person> getAll() {
        List<PersonEntity> allPersons = personService.getAll();
        return allPersons.stream().map(Person::persons).collect(Collectors.toList());
    }

    @GetMapping(value = "/person/{id}", produces = "application/json")
    public PersonEntity getById(@PathVariable Long id) {

        return personService.getById(id);
    }

    @PostMapping(value = "/person", consumes = "application/json", produces = "application/json")
    public PersonEntity create(@RequestBody PersonEntity personEntity) throws JsonProcessingException {
        return personService.create(personEntity);
    }

    @PutMapping(value = "/person/{id}", consumes = "application/json", produces = "application/json")
    public PersonEntity update(@RequestBody PersonEntity personEntity, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/findPerson/" + personEntity.getId()).toUriString());

        return personService.save(personEntity);
    }

    @GetMapping(value = "/activePersons", produces = "application/json")
    public List<Person> getActivePersons() {
        List<PersonEntity> activePersons = personService.getActivePersons();
        return activePersons.stream().map(Person::persons).collect(Collectors.toList());
    }

    @PutMapping(value = "/person/{id}/deactivate", produces = "application/json")
    public PersonEntity deactivatePerson(@PathVariable Long id) {
        PersonEntity personEntity = personService.getById(id);
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        personEntity.setIsActive(false);
        return personService.save(personEntity);
    }

    @PutMapping(value = "/person/{id}/activate", produces = "application/json")
    public PersonEntity activatePerson(@PathVariable Long id) {
        PersonEntity personEntity = personService.getById(id);
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        personEntity.setIsActive(true);
        return personService.save(personEntity);
    }

    @DeleteMapping(value = "/person/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) {
        PersonEntity personEntity = personService.getById(id);
        if (personEntity == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found");
        }
        personService.delete(personEntity);
    }
}
