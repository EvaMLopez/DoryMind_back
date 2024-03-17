package dev.eva.dorymind.tasks;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository <Task, Long> {

    //List<Task> findByAssignedTo(Long userId);
   
    @Query("SELECT t FROM Task t WHERE t.assignedUser.id = :userId")
    List<Task> findByAssignedUser(Long userId);

    List<Task> findByGroupId(Long groupId);
    
}