package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.PersonTaskRepository;

import java.util.List;

@Service
public class PersonTaskService {

    @Autowired
    PersonTaskRepository personTaskRepository;

    @Autowired
    PersonRepository personRepository;

    public List<PersonTaskEntity> getAllByPersonId(Long personId) {
        PersonEntity person = personRepository.findById(personId).orElse(null);

        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person with id " + personId + " not found");
        }

        return personTaskRepository.findByPerson(person);
    }

    public List<PersonTaskEntity> getAllByTask(TaskEntity task) {
        return personTaskRepository.findByTask(task);
    }

    public PersonTaskEntity getById(Long id) {
        return personTaskRepository.findById(id).orElse(null);
    }

    public PersonTaskEntity getByPersonAndTask(PersonEntity person, TaskEntity task) {
        return personTaskRepository.findByPersonAndTask(person, task);
    }

    public PersonTaskEntity save(PersonTaskEntity personTask) {
        return personTaskRepository.save(personTask);
    }

    public PersonTaskEntity toggleTaskCompletionStatus(Long taskId) {
        PersonTaskEntity personTaskEntity = getById(taskId);
        personTaskEntity.setIsCompleted(!personTaskEntity.getIsCompleted());
        return personTaskRepository.save(personTaskEntity);
    }

    public void delete(PersonTaskEntity personTask) {
        personTaskRepository.delete(personTask);
    }
}
