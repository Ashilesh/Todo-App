package io.sonkusle.TodoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.sonkusle.TodoApp.models.Todo;

public interface TodoRepo extends JpaRepository<Todo, Long>{
	
	
}
