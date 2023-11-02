package se.xlent.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.xlent.onboarding.entity.TaskTypeEntity;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskTypeEntity, Long> {

}