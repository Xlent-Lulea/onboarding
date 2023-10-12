package se.xlent.onboarding.repository;

import org.springframework.stereotype.Repository;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import se.xlent.onboarding.entity.TaskTypeEntity;
import se.xlent.onboarding.model.TaskType;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}