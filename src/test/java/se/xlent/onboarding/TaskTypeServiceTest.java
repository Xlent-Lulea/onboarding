package se.xlent.onboarding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.model.TaskType;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.repository.TaskTypeRepository;
import se.xlent.onboarding.service.TaskServiceImpl;
import se.xlent.onboarding.service.TaskTypeServiceImpl;
import se.xlent.onboarding.support.mapper.TaskMapper;
import se.xlent.onboarding.support.mapper.TaskTypeMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskTypeServiceTest {

    @Autowired
    private TaskMapper taskMapper;

    @Mock
    private TaskTypeMapper typeMapper;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskServiceImpl taskService;

    @Mock
    private TaskTypeRepository taskTypeRepository;

    @InjectMocks
    private TaskTypeServiceImpl taskTypeService;

    @Test
    void multipleTestDelete() {
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            testDelete(tasks);
            tasks.add(new Task());
            reset(taskService, taskTypeRepository); // Reset method call counters
        }
    }

    private void testDelete(List<Task> tasks) {
        TaskType taskTypeToDelete = new TaskType();
        taskTypeToDelete.setId(1L);
        when(taskRepository.findByTypeId(any())).thenReturn(taskMapper.toEntityList(tasks));

        taskTypeService.delete(taskTypeToDelete.getId());

        verify(taskService, times(tasks.size())).delete(any());
        verify(taskTypeRepository).deleteById(taskTypeToDelete.getId());
    }
}
