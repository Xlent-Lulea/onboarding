package se.xlent.onboarding.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.model.Task;
import se.xlent.onboarding.model.TaskType;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.repository.TaskTypeRepository;

import java.util.List;

@Service
public class TaskTypeService {
    private final TaskTypeRepository taskTypeRepository;

    public TaskTypeService(TaskTypeRepository taskTypeRepository) {
        this.taskTypeRepository = taskTypeRepository;
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
        taskTypeRepository.delete(taskTypeEntity);
    }
}
