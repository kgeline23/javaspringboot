package com.project.todowebservice.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.todowebservice.repositories.TodoRepository;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import com.project.todowebservice.models.Todo;

@RestController
@RequestMapping("/todo")
public class TodoController {
    
    /* */
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos()
    {
        try
        {
            if (todoRepository == null)
                throw new Exception("Repo not initialized");
            List<Todo> todoList = new ArrayList<Todo>();
            todoRepository.findAll().forEach(todoList::add);
            if (todoList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @GetMapping("/sort/name")
    public ResponseEntity<List<Todo>> sortTodoByName()
    {
        try
        {
            List<Todo> todoList = new ArrayList<Todo>();
            todoRepository.findAll(Sort.by(Sort.Direction.ASC, "name")).forEach(todoList::add);
            if (todoList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @GetMapping("/sort/id")
    public ResponseEntity<List<Todo>> sortTodoByID()
    {
        try
        {
            List<Todo> todoList = new ArrayList<Todo>();
            todoRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).forEach(todoList::add);
            if (todoList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoByID(@PathVariable("id") long id)
    {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo == null) 
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } 
        else return new ResponseEntity<>(todo.get(), HttpStatus.OK);
        
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Todo>> getTodosByName(@PathVariable("name") String name)
    {
        try
        {
            List<Todo> todoList = new ArrayList<Todo>();
            todoRepository.findByNameContaining(name).forEach(todoList::add);
            if (todoList.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(todoList, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }        
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Todo>> getTodoByStatus(@PathVariable("status") boolean status)
    {
        List<Todo> todoList = todoRepository.findByStatus(status);
        
        try {
            if (todoList.isEmpty()) 
            {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } 
            else { return new ResponseEntity<>(todoList, HttpStatus.OK); }    
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);        }
    }

    @PostMapping("/")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo)
    {
        try
        {
            Todo t = todoRepository.save(todo);
            t.setStatus(false);
            return new ResponseEntity<>(t, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> editTodo(@PathVariable("id") long id, @RequestBody Todo info)
    {
        Optional<Todo> todo = todoRepository.findById(id);

        if (todo.isPresent()) 
        {
            Todo t = todo.get();
            if(!info.getName().isEmpty())
            {
                t.setName(info.getName());
            }
            if(info.getStatus() != null)
            {
                t.setStatus(info.getStatus());
            }
            return new ResponseEntity<>(todoRepository.save(t), HttpStatus.OK);
            
        } 
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);   
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("id") long id)
    {
        try 
        {
            todoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } 
        catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
