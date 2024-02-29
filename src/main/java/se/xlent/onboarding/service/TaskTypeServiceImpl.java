package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.model.TaskType;
import se.xlent.onboarding.repository.TaskRepository;
import se.xlent.onboarding.repository.TaskTypeRepository;
import se.xlent.onboarding.support.mapper.TaskTypeMapper;

import java.util.List;

@Service
public class TaskTypeServiceImpl implements TaskTypeService {

    @Autowired
    private TaskTypeRepository taskTypeRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskTypeMapper typeMapper;

    public List<TaskType> getAll() {
        return typeMapper.toModelList(taskTypeRepository.findAll());
    }

    public TaskType getById(Long taskTypeId) {
        return typeMapper.toModel(taskTypeRepository.findById(taskTypeId).orElse(null));
    }

    public TaskType save(TaskType taskType) {
        return typeMapper.toModel(taskTypeRepository.save(typeMapper.toEntity(taskType)));
    }

    public void delete(Long id) {
        // Delete associated tasks (cascades to PersonTasks)
        List<TaskEntity> tasks = taskRepository.findByTypeId(id);
        for (TaskEntity task : tasks) {
            taskService.delete(task.getId());
        }

        taskTypeRepository.deleteById(id);
    }
}
