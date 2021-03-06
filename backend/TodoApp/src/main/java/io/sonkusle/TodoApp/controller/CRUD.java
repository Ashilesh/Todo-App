package io.sonkusle.TodoApp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	/*
	 * get all todos for specifc user
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/Todo/{userId}")
	public List<Todo> getAllTodos(@PathVariable(name = "userId") int userId) {
		return todoRepo.findByUserId(userId);
	}
	
	
	/*
	 * add todo for user
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/Todo/{userId}")
	public Todo addTodo(@PathVariable int userId,@RequestBody Todo todo, HttpServletResponse response) {
		
		todo.setUserId(userId);
		Todo createdTodo = todoRepo.save(todo);
		
		if(createdTodo != null)
			response.setStatus(HttpServletResponse.SC_CREATED);
		else
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		                      
		return createdTodo;
	}
	
	/*
	 * change iscomplete for todo
	 */
	@CrossOrigin(origins = "http://localhost:3000")
	@Transactional
	@PostMapping("/Todo/{userId}/{id}")
	public void changeCompleteTodo(@PathVariable(name= "userId") int userId,
			@PathVariable(name = "id") Long id,
			@RequestParam(name = "isComplete") boolean tf,
			HttpServletResponse response) {
		
		todoRepo.changeComplete(tf,userId,id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	
	@Transactional
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/Todo/{userId}/{id}")
	public void deleteTodo(@PathVariable int userId, 
			@PathVariable Long id) {
		
		todoRepo.deleteByIdAndUserId(userId, id);
		
	}

}
