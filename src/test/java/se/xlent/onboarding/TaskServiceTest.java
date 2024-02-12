package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.service.PersonService;
import se.xlent.onboarding.service.PersonTaskService;
import se.xlent.onboarding.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private PersonService personService;

    @Mock
    private PersonTaskService personTaskService;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    void multipleTestCreate() {
        List<PersonEntity> persons = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testCreate(persons);
            persons.add(new PersonEntity());
            reset(personTaskService, taskRepository); // Reset method call counters
        }
    }

    private void testCreate(List<PersonEntity> persons) {
        TaskEntity taskToCreate = new TaskEntity();
        when(personService.getAll()).thenReturn(persons);

        taskService.create(taskToCreate);

        verify(personTaskService, times(persons.size())).save(argThat(Objects::nonNull));
        verify(taskRepository).save(taskToCreate);
    }

    @Test
    void multipleTestSave() {
        List<PersonEntity> persons = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testSave(persons);
            persons.add(new PersonEntity());
            reset(personTaskService, taskRepository); // Reset method call counters
        }
    }

    private void testSave(List<PersonEntity> persons) {
        TaskEntity taskToSave = new TaskEntity();
        when(personService.getAll()).thenReturn(persons);
        when(personTaskService.getByPersonAndTask(any(), any())).thenAnswer(invocation -> {
            PersonTaskEntity personTask = new PersonTaskEntity();
            personTask.setTask(taskToSave);
            return personTask;
        });

        taskService.save(taskToSave);

        verify(personTaskService, times(persons.size())).save(argThat(personTask ->
                taskToSave.equals(personTask.getTask())));
        verify(taskRepository).save(taskToSave);
    }

    @Test
    void multipleTestDelete() {
        List<PersonEntity> persons = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testDelete(persons);
            persons.add(new PersonEntity());
            reset(personTaskService, taskRepository); // Reset method call counters
        }
    }

    private void testDelete(List<PersonEntity> persons) {
        TaskEntity taskToDelete = new TaskEntity();
        taskToDelete.setId(1L);
        when(taskService.getById(anyLong())).thenAnswer(invocation -> Optional.of(taskToDelete));
        when(personTaskService.getAllByTask(any())).thenAnswer(invocation -> {
            List<PersonTaskEntity> personTasks = new ArrayList<>();

            for (PersonEntity ignored : persons) {
                PersonTaskEntity personTask = new PersonTaskEntity();
                personTask.setTask(taskToDelete);
                personTasks.add(personTask);
            }

            return personTasks;
        });

        taskService.delete(taskToDelete.getId());

        verify(personTaskService, times(persons.size())).delete(argThat(personTask ->
                taskToDelete.equals(personTask.getTask())));
        verify(taskRepository).delete(taskToDelete);
    }
}
