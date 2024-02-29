package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.xlent.onboarding.model.Person;
import se.xlent.onboarding.model.PersonTask;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.support.mapper.PersonMapper;
import se.xlent.onboarding.support.mapper.TaskMapper;

import java.util.List;


@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private PersonTaskService personTaskService;
    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private TaskMapper taskMapper;

    public Person create(Person person) {
        person = personMapper.toModel(personRepository.save(personMapper.toEntity(person)));
        List<Task> allTasks = taskMapper.toModelList(taskRepository.findAll());

        for (Task task : allTasks) {
            PersonTask personTask = new PersonTask();
            personTask.updatePersonTaskValues(task, person);

            personTaskService.save(personTask);
        }

        return person;
    }
    public Person save(Person person) {
        return personMapper.toModel(personRepository.save(personMapper.toEntity(person)));
    }

    public Person getById(Long id) {
        return personMapper.toModel(personRepository.findById(id).orElse(null));
    }

    public List<Person> getActivePersons() {
        return personMapper.toModelList(personRepository.findByIsActiveTrue());
    }

    public List<Person> getAll() {
        return personMapper.toModelList(personRepository.findAll());
    }

    public void delete(Long id) {
        List<PersonTask> tasks = personTaskService.getAllByPersonId(id);

        for (PersonTask task : tasks) {
            personTaskService.delete(task.getId());
        }

        personRepository.deleteById(id);
    }

    public void resetTasks(Person person) {
        List<PersonTask> tasks = personTaskService.getAllByPersonId(person.getId());

        for (PersonTask task : tasks) {
            personTaskService.reset(task);
        }
    }
}
