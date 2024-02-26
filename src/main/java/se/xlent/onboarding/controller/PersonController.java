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
import se.xlent.onboarding.model.Person;
import se.xlent.onboarding.service.PersonService;

import java.util.List;


@RestController
@Validated
@CrossOrigin
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons", produces = "application/json")
    public List<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping(value = "/person/{id}", produces = "application/json")
    public Person getById(@PathVariable Long id) throws ResponseStatusException {
        Person person = personService.getById(id);

        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + id + " not found");
        }

        return person;
    }

    @PostMapping(value = "/person", consumes = "application/json", produces = "application/json")
    public Person create(@Valid @RequestBody Person person) {
        return personService.create(person);
    }

    @PutMapping(value = "/person/{id}", consumes = "application/json", produces = "application/json")
    public Person update(@Valid @RequestBody Person person, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/findPerson/" + person.getId()).toUriString());

        return personService.save(person);
    }

    @GetMapping(value = "/activePersons", produces = "application/json")
    public List<Person> getActivePersons() {
        return personService.getActivePersons();
    }

    @PutMapping(value = "/person/{id}/deactivate", produces = "application/json")
    public Person deactivatePerson(@PathVariable Long id) throws ResponseStatusException {
        Person person = personService.getById(id);

        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + id + " not found");
        }

        person.setIsActive(false);
        return personService.save(person);
    }

    @PutMapping(value = "/person/{id}/activate", produces = "application/json")
    public Person activatePerson(@PathVariable Long id) throws ResponseStatusException {
        Person person = personService.getById(id);

        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + id + " not found");
        }

        person.setIsActive(true);
        return personService.save(person);
    }

    @PutMapping(value = "/person/{id}/reset", produces = "application/json")
    public ResponseEntity<Void> resetTasks(@PathVariable Long id) throws ResponseStatusException {
        Person person = personService.getById(id);

        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + id + " not found");
        }

        personService.resetTasks(person);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/person/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResponseStatusException {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
