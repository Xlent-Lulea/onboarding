package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.repository.PersonRepository;

import java.util.List;


@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final TaskService taskService;
    private final PersonTaskService personTaskService;

    @Autowired
    public PersonService(
            PersonRepository personRepository,
            TaskService taskService,
            PersonTaskService personTaskService
    ) {
        this.personRepository = personRepository;
        this.taskService = taskService;
        this.personTaskService = personTaskService;
    }

    public PersonEntity create(PersonEntity personEntity) {
        personEntity.setIsActive(true);
        List<TaskEntity> allTasks = taskService.getAll();

        for(TaskEntity task : allTasks) {
            PersonTaskEntity personTask = new PersonTaskEntity();
            personTask.setPersonId(personEntity.getId());
            personTask.setTaskId(task.getId());
            personTask.setCompletionStatus(false);

            personTaskService.save(personEntity, personTask); // You'll need to implement this method in your service
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
