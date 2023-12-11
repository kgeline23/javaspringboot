package com.project.todowebservice.models;

import org.springframework.lang.Nullable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Boolean status;

    public Todo() {}

    public Todo(String name, Boolean status) { 
        this.name = name;        
        this.status = status; 
    } 

    public Long getId() { 
        return id; 
    } 
    public void setId(Long id) { 
        this.id = id; 
    } 
    public String getName() { 
        return name; 
    } 
    public void setName(String name) { 
        this.name = name; 
    } 
    public Boolean getStatus() { 
        return status; 
    } 
    public void setStatus(boolean status) { 
        this.status = status; 
    } 
    public String toString() {
        return this.name + "(#" + Long.toString(this.id) + ")";
    }
}
