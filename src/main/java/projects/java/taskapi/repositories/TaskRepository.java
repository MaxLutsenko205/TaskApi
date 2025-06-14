package projects.java.taskapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import projects.java.taskapi.models.Status;
import projects.java.taskapi.models.Task;
import projects.java.taskapi.models.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);
    List<Task> findByUserAndStatus(User user, Status status);
    Optional<Task> findByUserAndId(User user, Long id);
}
