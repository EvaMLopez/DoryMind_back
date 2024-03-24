package dev.eva.dorymind.tasks;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.eva.dorymind.security.SecurityUser;
import dev.eva.dorymind.users.User;
import dev.eva.dorymind.users.UserService;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("${api-endpoint}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks = taskService.findAll();
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for (Task task : tasks) {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(task.getId());
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDeadline(task.getDeadline());
            taskDTO.setAssignedUserId(task.getAssignedUser().getId());
            taskDTO.setUrgent(task.isUrgent());
            taskDTO.setCompleted(task.isCompleted());
            taskDTOs.add(taskDTO);
        }
        return taskDTOs;
    }    

    @GetMapping("/my-tasks")
    public List<TaskDTO> getMyTasks(@AuthenticationPrincipal SecurityUser securityUser) {
        Long userId = securityUser.getId();
        return taskService.getTasksAssignedToUser(userId);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO newTaskDTO, @AuthenticationPrincipal SecurityUser securityUser) {
        Task newTask = taskService.convertToTask(newTaskDTO);
        User assignedUser = userService.findById(newTaskDTO.getAssignedUserId());
        newTask.setAssignedUser(assignedUser);
        Task savedTask = taskService.create(newTask);
        TaskDTO savedTaskDTO = new TaskDTO(savedTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTaskDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO updatedTaskDTO) {
        Task updatedTask = taskService.convertToTask(updatedTaskDTO);
        updatedTask.setId(id);
        Task savedTask = taskService.update(updatedTask);
        TaskDTO savedTaskDTO = new TaskDTO(savedTask);
        return ResponseEntity.ok(savedTaskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id, @AuthenticationPrincipal SecurityUser securityUser) {
        User user = userService.findById(securityUser.getId());
        if (user != null) {
            try {
                taskService.deleteTask(id, user);
                return ResponseEntity.ok("Tarea eliminada");
            } catch (UnauthorizedException e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
            } catch (TaskNotFoundException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}

