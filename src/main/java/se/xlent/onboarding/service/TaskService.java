package se.xlent.onboarding.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskType;
import se.xlent.onboarding.repository.PersonRepository;
import se.xlent.onboarding.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {


    private TaskRepository taskRepository;

    private PersonRepository personRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }


    public TaskEntity saveUpdateTask(PersonEntity personEntity, TaskEntity taskEntity) {
        personEntity.getTaskEntities().add(taskEntity);
        return taskRepository.save(taskEntity);
    }

    public TaskEntity getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Task not found" + id));
    }



    public TaskEntity findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<TaskEntity> getTasksByType(TaskType taskType) {
        return taskRepository.findByTaskType(taskType);
    }


    public List<TaskEntity> getTasksByPerson(Long personId) {
        return taskRepository.findByPerson(personId);
    }

    public List<TaskEntity> getTasksByPersonAndTaskType(PersonEntity personEntity, TaskType taskType) {
        return taskRepository.findByPersonAndTaskType(personEntity, taskType);
    }

    public List<TaskEntity> saveAllTasks(List<TaskEntity> taskEntities) {
        return taskRepository.saveAll(taskEntities);
    }

    public void deleteTask(Long taskId) {
        TaskEntity taskEntity = getTaskById(taskId);
        taskRepository.delete(taskEntity);
    }
}
