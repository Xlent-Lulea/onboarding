package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    PersonService personService;
    @Autowired
    PersonTaskService personTaskService;
    @Autowired
    TaskTypeService taskTypeService;

    public List<TaskEntity> getAll() {
        return taskRepository.findAll();
    }

    public TaskEntity getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Task not found" + id));
    }

    public TaskEntity create(TaskEntity taskEntity) {
        List<PersonEntity> persons = personService.getAll();

        for (PersonEntity person : persons) {
            PersonTaskEntity personTask =
                    new PersonTaskEntity()
                            .updatePersonTaskValues(taskEntity, person);

            personTaskService.save(person, personTask);
            personService.save(person);
        }

        return taskRepository.save(taskEntity);
    }

    public TaskEntity save(TaskEntity taskEntity) {
        List<PersonEntity> persons = personService.getAll();

        for (PersonEntity person : persons) {

            PersonTaskEntity personTask =
                    personTaskService.getByPersonAndTask(person, taskEntity)
                            .updatePersonTaskValues(taskEntity, person);

            personTaskService.save(person, personTask);
            personService.save(person);
        }
        return taskRepository.save(taskEntity);
    }

    public void delete(Long taskId) {
        TaskEntity taskEntity = getById(taskId);
        List<PersonEntity> persons = personService.getAll();

        for (PersonEntity person : persons) {
            PersonTaskEntity personTask =
                    personTaskService.getByPersonAndTask(person, taskEntity);

            personService.removePersonTask(person, personTask);
            personTaskService.delete(personTask);
        }

        taskRepository.delete(taskEntity);
    }
}
