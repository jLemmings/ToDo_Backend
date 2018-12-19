package ch.aintevenmad.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    @Query(value = "SELECT * FROM to_do WHERE task_completed = true", nativeQuery = true)
    Collection<ToDo> completedTasks();

    Collection<ToDo> findByTaskCompletedIsTrue();

    Collection<ToDo> findByTaskCompletedIsFalse();

}

