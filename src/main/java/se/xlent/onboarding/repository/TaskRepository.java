package se.xlent.onboarding.repository;

import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.model.TaskType;

import java.util.List;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}