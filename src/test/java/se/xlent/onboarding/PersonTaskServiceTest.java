package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.PersonTaskRepository;
import se.xlent.onboarding.service.PersonTaskServiceImpl;
import se.xlent.onboarding.support.mapper.PersonTaskMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PersonTaskServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private PersonTaskRepository personTaskRepository;
    @Mock
    private PersonTaskMapper personTaskMapper;

    @InjectMocks
    private PersonTaskServiceImpl personTaskService;

    @Test
    void testGetAllByPersonId() {
        Long id = 1L;
        PersonEntity person = new PersonEntity();
        person.setId(id);
        List<PersonTaskEntity> tasks = new ArrayList<>();
        when(personTaskRepository.findByPersonId(any())).thenReturn(tasks);

        personTaskService.getAllByPersonId(id);

        verify(personTaskRepository).findByPersonId(id);
    }
}
