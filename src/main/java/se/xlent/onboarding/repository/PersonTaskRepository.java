package se.xlent.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;
import se.xlent.onboarding.model.TaskType;

import java.util.List;

@Repository
public interface PersonTaskRepository extends JpaRepository<PersonTaskEntity, Long> {
    List<PersonTaskEntity> findByPersonId(Long personId);

    PersonTaskEntity findByPersonIdAndTaskId(Long personId, Long taskId);

    List<PersonTaskEntity> findByTaskId(Long taskId);
}
