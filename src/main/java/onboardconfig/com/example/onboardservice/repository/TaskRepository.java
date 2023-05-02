package onboardconfig.com.example.onboardservice.repository;

import onboardconfig.com.example.onboardservice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}