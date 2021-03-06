package io.sonkusle.TodoApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.sonkusle.TodoApp.models.Todo;

public interface TodoRepo extends JpaRepository<Todo, Long>{
	
	public List<Todo> findByUserId(int userId);
	
	@Modifying
	@Query("update Todo t set t.isComplete =:tf where t.userId =:userId AND t.id =:id")
	public void changeComplete(@Param("tf") boolean tf, @Param("userId") int userId, @Param("id") Long id);
	
	@Modifying
	@Query("delete Todo t where t.userId =:userId AND t.id =:id")
	public void deleteByIdAndUserId(@Param("userId") int userId, @Param("id") Long id);
	
	
}
