package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.repository.TaskTypeRepository;

import java.util.List;

@Service
public class TaskTypeService {

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    public List<TaskTypeEntity> getAll() {
        return taskTypeRepository.findAll();
    }

    public TaskTypeEntity getById(Long taskTypeId) {
        return taskTypeRepository.findById(taskTypeId).orElse(null);
    }

    public TaskTypeEntity save(TaskTypeEntity taskType) {
        return taskTypeRepository.save(taskType);
    }

    public void delete(Long taskTypeId) throws NotFoundException {
        TaskTypeEntity taskType = getById(taskTypeId);

        if (taskType == null) {
            throw new NotFoundException("TaskType with id " + taskTypeId + " not found");
        }

        taskTypeRepository.delete(taskType);
    }
}
