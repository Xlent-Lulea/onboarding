package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import se.xlent.onboarding.model.Person;
import se.xlent.onboarding.model.PersonTask;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.model.TaskType;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.service.*;
import se.xlent.onboarding.support.mapper.TaskMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private PersonServiceImpl personService;

    @Mock
    private PersonTaskServiceImpl personTaskService;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void multipleTestCreate() {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testCreate(persons);
            persons.add(new Person());
            reset(personTaskService, taskRepository); // Reset method call counters
        }
    }

    private void testCreate(List<Person> persons) {
        Task taskToCreate = new Task();
        taskToCreate.setType(new TaskType());
        when(personService.getAll()).thenReturn(persons);

        taskService.create(taskToCreate);

        verify(personTaskService, times(persons.size())).save(argThat(Objects::nonNull));
        verify(taskRepository).save(any());
    }

    @Test
    void multipleTestSave() {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testSave(persons);
            persons.add(new Person());
            reset(personTaskService, taskRepository); // Reset method call counters
        }
    }

    private void testSave(List<Person> persons) {
        Task taskToSave = new Task();
        taskToSave.setType(new TaskType());
        taskToSave.getType().setId(1L);
        when(personService.getAll()).thenReturn(persons);
        when(personTaskService.getByPersonIdAndTaskId(any(), any())).thenAnswer(invocation -> {
            PersonTask personTask = new PersonTask();
            personTask.setTask(taskToSave);
            return personTask;
        });

        taskService.save(taskToSave);

        verify(personTaskService, times(persons.size())).save(argThat(personTask ->
                taskToSave.equals(personTask.getTask())));
        verify(taskRepository).save(any());
    }

    @Test
    void multipleTestDelete() {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testDelete(persons);
            persons.add(new Person());
            reset(personTaskService, taskRepository); // Reset method call counters
        }
    }

    private void testDelete(List<Person> persons) {
        Long id = 1L;
        Task taskToDelete = new Task();
        taskToDelete.setId(id);
        taskToDelete.setType(new TaskType());
        when(personTaskService.getAllByTaskId(anyLong())).thenAnswer(invocation -> {
            List<PersonTask> personTasks = new ArrayList<>();

            for (Person ignored : persons) {
                PersonTask personTask = new PersonTask();
                personTask.setTask(taskToDelete);
                personTasks.add(personTask);
            }

            return personTasks;
        });

        taskService.delete(id);

        verify(personTaskService, times(persons.size())).delete(any());
        verify(taskRepository).deleteById(id);
    }
}
