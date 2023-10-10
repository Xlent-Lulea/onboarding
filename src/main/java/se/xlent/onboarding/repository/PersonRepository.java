package se.xlent.onboarding.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.xlent.onboarding.entity.PersonEntity;

import java.util.List;


public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findByIsActiveTrue();
}
