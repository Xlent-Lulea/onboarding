package se.xlent.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.xlent.onboarding.entity.TaskTypeEntity;

import java.util.List;


public interface TaskTypeRepository extends JpaRepository<TaskTypeEntity, Long> {

}