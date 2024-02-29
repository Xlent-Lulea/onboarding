package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.xlent.onboarding.model.Person;
import se.xlent.onboarding.model.PersonTask;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.service.PersonServiceImpl;
import se.xlent.onboarding.service.PersonTaskService;
import se.xlent.onboarding.support.mapper.PersonMapper;
import se.xlent.onboarding.support.mapper.PersonTaskMapper;
import se.xlent.onboarding.support.mapper.TaskMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonTaskService personTaskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @Mock
    private TaskMapper taskMapper;

    @Mock
    private PersonTaskMapper personTaskMapper;

    @InjectMocks
    private PersonServiceImpl personService;


    @Test
    void multipleTestCreate() {
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testCreate(tasks);
            tasks.add(new Task());
            reset(personTaskService, personRepository); // Reset method call counters
        }
    }

    private void testCreate(List<Task> tasks) {
        Person personToCreate = new Person();
        personToCreate.setId(1L + tasks.size());
        when(taskMapper.toModelList(anyList())).thenReturn(tasks);

        personService.create(personToCreate);

        verify(personTaskService, times(tasks.size())).save(any());
        verify(personRepository).save(any());
    }

    @Test
    void multipleTestDelete() {
        List<PersonTask> personTasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testDelete(personTasks);
            personTasks.add(new PersonTask());
            reset(personTaskService, personRepository); // Reset method call counters
        }
    }

    private void testDelete(List<PersonTask> personTasks) {
        Person personToDelete = new Person();
        personToDelete.setId(1L + personTasks.size());
        when(personTaskService.getAllByPersonId(any())).thenReturn(personTasks);

        personService.delete(personToDelete.getId());

        verify(personTaskService, times(personTasks.size())).delete(any());
        verify(personRepository).deleteById(personToDelete.getId());
    }
}
