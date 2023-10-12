package se.xlent.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.xlent.onboarding.entity.TaskTypeEntity;

import java.util.List;

@Repository
public interface TaskTypeRepository extends JpaRepository<TaskTypeEntity, Long> {

}