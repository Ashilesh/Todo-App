package io.sonkusle.TodoApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.sonkusle.TodoApp.models.Todo;
import io.sonkusle.TodoApp.repository.TodoRepo;

@RestController
public class CRUD {

	@Autowired
	private TodoRepo todoRepo;

	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}

	// get all todos for all user
	// change implementation
	@GetMapping("/Todo")
	public List<Todo> getAllTodos() {
		return todoRepo.findAll();
	}
	
	@PostMapping("/Todo")
	public Todo addTodo(@RequestBody Todo todo, HttpServletResponse response) {
		
		Todo createdTodo = todoRepo.save(todo);
		
		if(createdTodo != null)
			response.setStatus(HttpServletResponse.SC_CREATED);
		else
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		                      
		return createdTodo;
	}
	
//	@PostMapping("/Todo/{userId}/{id}")
//	public Todo updateTodo(@PathVariable(name = "userId") int userId, Long id) {
//		Todo changeTodo = todoRepo.find;
//		changeTodo.setComplete(!changeTodo.isComplete());
//		
//		return todoRepo.save(changeTodo);
//	}

}