package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.repository.TaskTypeRepository;
import se.xlent.onboarding.service.TaskService;
import se.xlent.onboarding.service.TaskTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskTypeServiceTest {


    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskService taskService;

    @Mock
    private TaskTypeRepository taskTypeRepository;

    @InjectMocks
    private TaskTypeService taskTypeService;

    @Test
    void multipleTestDelete() {
        List<TaskEntity> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testDelete(tasks);
            tasks.add(new TaskEntity());
            reset(taskService, taskTypeRepository); // Reset method call counters
        }
    }

    private void testDelete(List<TaskEntity> tasks) {
        TaskTypeEntity taskTypeToDelete = new TaskTypeEntity();
        taskTypeToDelete.setId(1L);
        when(taskTypeService.getById(anyLong())).thenAnswer(invocation -> Optional.of(taskTypeToDelete));
        when(taskRepository.findByType(any())).thenReturn(tasks);

        taskTypeService.delete(taskTypeToDelete.getId());

        verify(taskService, times(tasks.size())).delete(any());
        verify(taskTypeRepository).delete(taskTypeToDelete);
    }
}
