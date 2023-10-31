package se.xlent.onboarding.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.repository.TaskTypeRepository;

import java.util.List;

@Service
public class TaskTypeService {
    private final TaskTypeRepository taskTypeRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public TaskTypeService(TaskTypeRepository taskTypeRepository, TaskRepository taskRepository, TaskService taskService) {
        this.taskTypeRepository = taskTypeRepository;
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    public List<TaskTypeEntity> getAll() {
        List<TaskTypeEntity> types = taskTypeRepository.findAll();
        return types;
    }

    public TaskTypeEntity getById(Long taskTypeId) {
        return taskTypeRepository.findById(taskTypeId).orElseThrow(() ->
                new ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND,
                        "TaskType not found" + taskTypeId
                ));
    }

    public TaskTypeEntity save(TaskTypeEntity taskTypeEntity) {
        return taskTypeRepository.save(taskTypeEntity);
    }

    public void delete(Long taskTypeId) {
        TaskTypeEntity taskTypeEntity = getById(taskTypeId);

        // Delete associated tasks (cascades to PersonTasks)
        List<TaskEntity> tasks = taskRepository.findByType(taskTypeEntity);
        for (TaskEntity task : tasks) {
            taskService.delete(task.getId());
        }

        taskTypeRepository.delete(taskTypeEntity);
    }
    public TaskTypeEntity updateName(Long taskTypeId, String newName) {
        TaskTypeEntity taskTypeEntity = getById(taskTypeId);
        taskTypeEntity.setName(newName);
        return taskTypeRepository.save(taskTypeEntity);
    }

}
