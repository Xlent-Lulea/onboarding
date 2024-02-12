package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.service.PersonService;
import se.xlent.onboarding.service.PersonTaskService;

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

    @InjectMocks
    private PersonService personService;


    @Test
    void multipleTestCreate() {
        List<TaskEntity> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testCreate(tasks);
            tasks.add(new TaskEntity());
            reset(personTaskService, personRepository); // Reset method call counters
        }
    }

    private void testCreate(List<TaskEntity> tasks) {
        PersonEntity personToCreate = new PersonEntity();
        when(taskRepository.findAll()).thenReturn(tasks);

        personService.create(personToCreate);

        verify(personTaskService, times(tasks.size())).save(any());
        verify(personRepository).save(personToCreate);
    }

    @Test
    void multipleTestDelete() {
        List<PersonTaskEntity> personTasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testDelete(personTasks);
            personTasks.add(new PersonTaskEntity());
            reset(personTaskService, personRepository); // Reset method call counters
        }
    }

    private void testDelete(List<PersonTaskEntity> personTasks) {
        PersonEntity personToDelete = new PersonEntity();
        when(personTaskService.getAllByPersonId(any())).thenReturn(personTasks);

        personService.delete(personToDelete);

        verify(personTaskService, times(personTasks.size())).delete(any());
        verify(personRepository).delete(personToDelete);
    }
}
