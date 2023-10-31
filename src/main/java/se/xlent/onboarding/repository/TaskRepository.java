package se.xlent.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.entity.TaskTypeEntity;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByType(TaskTypeEntity taskType);
}
