package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
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

    public List<TaskEntity> getAll() {
        return taskRepository.findAll();
    }

    public TaskEntity getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Task not found" + id));
    }

    public TaskEntity create(TaskEntity taskEntity) {
        taskEntity = taskRepository.save(taskEntity);

        List<PersonEntity> persons = personService.getAll();

        for (PersonEntity person : persons) {
            PersonTaskEntity personTask =
                    new PersonTaskEntity()
                            .updatePersonTaskValues(taskEntity, person);

            personTaskService.save(person, personTask);
        }

        return taskEntity;
    }

    public TaskEntity save(TaskEntity taskEntity) {
        List<PersonEntity> persons = personService.getAll();

        for (PersonEntity person : persons) {
            PersonTaskEntity personTask =
                    personTaskService.getByPersonAndTask(person, taskEntity)
                            .updatePersonTaskValues(taskEntity, person);

            personTaskService.save(person, personTask);
        }
        return taskRepository.save(taskEntity);
    }

    public void delete(Long taskId) {
        TaskEntity taskEntity = getById(taskId);
        List<PersonTaskEntity> personTasks = personTaskService.getAllByTask(taskEntity);

        for (PersonTaskEntity personTask : personTasks) {
            personTaskService.delete(personTask);
        }

        taskRepository.delete(taskEntity);
    }
}
