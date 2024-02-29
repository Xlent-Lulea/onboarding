package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.xlent.onboarding.model.Person;
import se.xlent.onboarding.model.PersonTask;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.support.mapper.TaskMapper;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonTaskService personTaskService;
    @Autowired
    private TaskMapper taskMapper;

    public List<Task> getAll() {
        return taskMapper.toModelList(taskRepository.findAll());
    }

    public Task getById(Long id) {
        return taskMapper.toModel(taskRepository.findById(id).orElse(null));
    }

    public Task create(Task task) {
        task = taskMapper.toModel(taskRepository.save(taskMapper.toEntity(task)));

        List<Person> persons = personService.getAll();

        for (Person person : persons) {
            PersonTask personTask =
                    new PersonTask()
                            .updatePersonTaskValues(task, person);

            personTaskService.save(personTask);
        }

        return task;
    }

    public Task save(Task task) {
        List<Person> persons = personService.getAll();

        for (Person person : persons) {
            if (task.getType().getId() == 0) {
                continue;
            }
            PersonTask personTask =
                    personTaskService.getByPersonIdAndTaskId(person.getId(), task.getId())
                            .updatePersonTaskValues(task, person);

            personTaskService.save(personTask);
        }
        return taskMapper.toModel(taskRepository.save(taskMapper.toEntity(task)));
    }

    public void delete(Long id) {
        List<PersonTask> personTasks = personTaskService.getAllByTaskId(id);
        for (PersonTask personTask : personTasks) {
            personTaskService.delete(personTask.getId());
        }

        taskRepository.deleteById(id);
    }
}
