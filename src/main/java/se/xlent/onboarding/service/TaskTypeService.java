package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.repository.TaskTypeRepository;

import java.util.List;

@Service
public class TaskTypeService {

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    public List<TaskTypeEntity> getAll() {
        return taskTypeRepository.findAll();
    }

    public TaskTypeEntity getById(Long taskTypeId) {
        return taskTypeRepository.findById(taskTypeId).orElse(null);
    }

    public TaskTypeEntity save(TaskTypeEntity taskType) {
        return taskTypeRepository.save(taskType);
    }

    public void delete(Long taskTypeId) throws ResponseStatusException {
        TaskTypeEntity taskType = getById(taskTypeId);

        if (taskType == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TaskType with id " + taskTypeId + " not found");
        }

        // Delete associated tasks (cascades to PersonTasks)
        List<TaskEntity> tasks = taskRepository.findByType(taskType);
        for (TaskEntity task : tasks) {
            taskService.delete(task.getId());
        }

        taskTypeRepository.delete(taskType);
    }
}
