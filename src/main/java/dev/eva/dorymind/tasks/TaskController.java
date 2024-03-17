package dev.eva.dorymind.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.eva.dorymind.users.User;
import dev.eva.dorymind.users.UserService;

@CrossOrigin(origins = "http://localhost:5173") // ** ACTUALIZAR ** //
@RestController
@RequestMapping("${api-endpoint}/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    @Autowired
    private UserService userService;
    
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }    

    @PostMapping
    public Task createTask(@RequestBody Task newTask) {
        return taskService.update(newTask);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        updatedTask.setId(id); 
        return taskService.update(updatedTask);
    }

    public void deleteTask(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails; 
        taskService.deleteTask(id, user);
    }

    @GetMapping("/my-tasks")
    public List<Task> getMyTasks(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails; // obtiene id del usuario actualmente autenticado
        Long userId = user.getId();
        return taskService.getTasksAssignedToUser(userId); // obtiene tareas asignadas al usuario
    }

    @GetMapping("/all-group-tasks")
    public List<Task> getAllGroupTasks(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userDetails;  // obtiene id del usuario actualmente autenticado 
        Long userId = user.getId();
        Long groupId = userService.getGroupIdByUserId(userId); // obtiene el grupo del usuario
        return taskService.getAllTasksInGroup(groupId); // obtiene todas las tareas del grupo
    }
}

