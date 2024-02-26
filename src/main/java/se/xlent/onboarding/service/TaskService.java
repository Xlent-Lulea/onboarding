package se.xlent.onboarding.service;

import se.xlent.onboarding.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAll();

    Task getById(Long id);

    Task create(Task task);

    Task save(Task task);

    void delete(Long id);
}
