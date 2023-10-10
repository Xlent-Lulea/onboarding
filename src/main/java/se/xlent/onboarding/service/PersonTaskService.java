package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.PersonTaskRepository;

import java.util.List;

@Service
public class PersonTaskService {

    @Autowired
    private PersonTaskRepository personTaskRepository;

    @Autowired
    private PersonRepository personRepository;

    public List<PersonTaskEntity> getAllByPersonId(Long personId) {
        PersonEntity person = personRepository.findById(personId).orElse(null);
        return personTaskRepository.findByPerson(person);
        // return personTaskRepository.findByPersonId(personId);
    }

    public PersonTaskEntity getById(Long id) {
        return personTaskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                org.springframework.http.HttpStatus.NOT_FOUND, "PersonTask not found " + id));
    }

    public PersonTaskEntity save(PersonEntity person, PersonTaskEntity personTask) {
        return personTaskRepository.save(personTask);
    }

    public PersonTaskEntity toggleTaskCompletionStatus(Long taskId) {
        PersonTaskEntity personTaskEntity = getById(taskId);
        personTaskEntity.setCompletionStatus(!personTaskEntity.getCompletionStatus());
        return personTaskRepository.save(personTaskEntity);
    }
}
