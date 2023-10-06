package se.xlent.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.xlent.onboarding.entity.PersonEntity;
import se.xlent.onboarding.entity.PersonTaskEntity;
import se.xlent.onboarding.entity.TaskEntity;

import java.util.List;

public interface PersonTaskRepository extends JpaRepository<PersonTaskEntity, Long> {

    List<PersonTaskEntity> findByPersonId(Long personId);

    PersonTaskEntity findByPersonIdAndTaskId(Long personId, Long taskId);

    List<PersonEntity> findPersonsByTaskIdAndIsCompletedTrue(Long taskId);
}
