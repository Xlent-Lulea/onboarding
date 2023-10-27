package se.xlent.onboarding.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.service.PersonService;

import java.util.List;


@RestController
@CrossOrigin(origins ="*")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons", produces = "application/json")
    public List<PersonEntity> getAll() {
        return personService.getAll();
    }

    @GetMapping(value = "/person/{id}", produces = "application/json")
    public PersonEntity getById(@PathVariable Long id) throws ResponseStatusException {
        PersonEntity person = personService.getById(id);

        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + id + " not found");
        }

        return person;
    }

    @PostMapping(value = "/person", consumes = "application/json", produces = "application/json")
    public PersonEntity create(@Valid @RequestBody PersonEntity person) {
        return personService.create(person);
    }

    @PutMapping(value = "/person/{id}", consumes = "application/json", produces = "application/json")
    public PersonEntity update(@Valid @RequestBody PersonEntity person, HttpServletResponse response) {
        response.setHeader("Location", ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/findPerson/" + person.getId()).toUriString());

        return personService.save(person);
    }

    @GetMapping(value = "/activePersons", produces = "application/json")
    public List<PersonEntity> getActivePersons() {
        return personService.getActivePersons();
    }

    @PutMapping(value = "/person/{id}/deactivate", produces = "application/json")
    public PersonEntity deactivatePerson(@PathVariable Long id) throws ResponseStatusException {
        PersonEntity person = personService.getById(id);

        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + id + " not found");
        }

        person.setIsActive(false);
        return personService.save(person);
    }

    @PutMapping(value = "/person/{id}/activate", produces = "application/json")
    public PersonEntity activatePerson(@PathVariable Long id) throws ResponseStatusException {
        PersonEntity person = personService.getById(id);

        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + id + " not found");
        }

        person.setIsActive(true);
        return personService.save(person);
    }

    @DeleteMapping(value = "/person/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ResponseStatusException {
        PersonEntity person = personService.getById(id);

        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + id + " not found");
        }

        personService.delete(person);
        return ResponseEntity.noContent().build();
    }
}
