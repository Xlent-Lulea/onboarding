package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.PersonTaskRepository;
import se.xlent.onboarding.service.PersonTaskService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonTaskServiceTest {

    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonTaskRepository personTaskRepository;


    @InjectMocks
    private PersonTaskService personTaskService;

    @Test
    void testGetAllByPersonId() {
        PersonEntity person = new PersonEntity();
        when(personRepository.findById(any())).thenReturn(Optional.of(person));

        personTaskService.getAllByPersonId(1L);

        verify(personTaskRepository).findByPerson(person);
    }

    @Test
    void testGetAllByPersonIdException() {
        assertThrows(ResponseStatusException.class, () -> personTaskService.getAllByPersonId(anyLong()));
    }
}
