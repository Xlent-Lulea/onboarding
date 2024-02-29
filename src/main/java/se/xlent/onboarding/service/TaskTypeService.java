package se.xlent.onboarding.service;

import se.xlent.onboarding.model.TaskType;

import java.util.List;

public interface TaskTypeService {

    List<TaskType> getAll();

    TaskType getById(Long taskTypeId);

    TaskType save(TaskType taskType);

    void delete(Long id);
}
