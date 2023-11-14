package se.xlent.onboarding.repository;

import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import se.xlent.onboarding.entity.TaskType;

import java.util.List;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByTaskType(TaskType taskType);

    List<TaskEntity> findByPersonId(Long personId);
    List<TaskEntity> findByPersonAndTaskType(PersonEntity personEntity, TaskType taskType);


}