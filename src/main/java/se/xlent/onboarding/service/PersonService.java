package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.TaskRepository;

import java.util.List;


@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    PersonTaskService personTaskService;

    public PersonEntity create(PersonEntity personEntity) {
        personEntity.setIsActive(true);
        List<TaskEntity> allTasks = taskRepository.findAll();

        for (TaskEntity task : allTasks) {
            PersonTaskEntity personTask = new PersonTaskEntity();
            personTask.updatePersonTaskValues(task, personEntity);

            personTaskService.save(personEntity, personTask);
        }

        return personRepository.save(personEntity);
    }
    public PersonEntity save(PersonEntity personEntity) {
        return personRepository.save(personEntity);
    }

    public PersonEntity getById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<PersonEntity> getActivePersons() {
        return personRepository.findByIsActiveTrue();
    }

    public List<PersonEntity> getAll() {
        return personRepository.findAll();
    }

    public void delete(PersonEntity personEntity) {
        personRepository.delete(personEntity);
    }
}
