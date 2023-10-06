package se.xlent.onboarding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.repository.PersonTaskRepository;

import java.util.List;

@Service
public class PersonTaskService {

    @Autowired
    private PersonTaskRepository personTaskRepository;

    // 1. List all tasks assigned to a person
    public List<PersonTaskEntity> findTasksForPerson(Long personId) {
        return personTaskRepository.findByPersonId(personId);
    }

    // 2. Mark a specific task as completed for a specific person
    public void markTaskAsCompleted(Long personId, Long taskId) {
        PersonTaskEntity personTask = personTaskRepository.findByPersonIdAndTaskId(personId, taskId);
        if (personTask != null) {
            personTask.setIsCompleted(true);
            personTaskRepository.save(personTask);
        }
        // Handle cases where the personTask is not found, maybe throw an exception
    }

    // 3. List all persons who have completed a specific task
    public List<PersonEntity> findPersonsWhoCompletedTask(Long taskId) {
        return personTaskRepository.findPersonsByTaskIdAndIsCompletedTrue(taskId);
    }
}
