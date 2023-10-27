package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
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
        return taskRepository.findById(id).orElse(null);
    }

    public TaskEntity create(TaskEntity task) {
        task = taskRepository.save(task);

        List<PersonEntity> persons = personService.getAll();

        for (PersonEntity person : persons) {
            PersonTaskEntity personTask =
                    new PersonTaskEntity()
                            .updatePersonTaskValues(task, person);

            personTaskService.save(personTask);
        }

        return task;
    }

    public TaskEntity save(TaskEntity task) {
        List<PersonEntity> persons = personService.getAll();

        for (PersonEntity person : persons) {
            PersonTaskEntity personTask =
                    personTaskService.getByPersonAndTask(person, task)
                            .updatePersonTaskValues(task, person);

            personTaskService.save(personTask);
        }
        return taskRepository.save(task);
    }

    public void delete(Long taskId) throws NotFoundException {
        TaskEntity task = getById(taskId);

        if (task == null) {
            throw new NotFoundException("Task with id " + taskId + " not found");
        }

        List<PersonTaskEntity> personTasks = personTaskService.getAllByTask(task);

        for (PersonTaskEntity personTask : personTasks) {
            personTaskService.delete(personTask);
        }

        taskRepository.delete(task);
    }
}
