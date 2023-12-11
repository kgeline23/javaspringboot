package com.project.todowebservice.repositories;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.project.todowebservice.models.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    // Todo findById(long id);
    List<Todo> findByNameContaining(String name);
    List<Todo> findByStatus(boolean status);
    
}
