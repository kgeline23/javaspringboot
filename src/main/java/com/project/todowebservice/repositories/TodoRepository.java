package com.project.todowebservice.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.project.todowebservice.models.Todo;
/**
 * Repository interface for managing Todo entities.
 * Extends CrudRepository for basic CRUD operations.
 * Provides additional query methods for finding Todos by name and status.
 */

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    List<Todo> findByNameContaining(String name);
    List<Todo> findByStatus(boolean status);
    
}
