package com.project.todowebservice.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.project.todowebservice.models.Todo;
/**
 * Repository interface for managing Todo entities.
 * Extends CrudRepository for basic CRUD operations.
 * Provides additional query methods for finding Todos by name and status.
 */

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByNameContaining(String name);
    List<Todo> findByStatus(boolean status);
    // List<Todo> sortByName(String name, Sort sortByName);
    // List<Todo> sortByID(Long id, Sort sortByID);

    
}
