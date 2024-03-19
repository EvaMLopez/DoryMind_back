package dev.eva.dorymind.tasks;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.eva.dorymind.users.User;
import dev.eva.dorymind.users.UserService;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserService userService;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }    
/* 
    public List<TaskDTO> getTasksAssignedToUser(Long userId) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getAssignedUser().getId().equals(userId))
                .map(task -> new TaskDTO(task))
                .collect(Collectors.toList());
    } */

    public List<TaskDTO> getTasksAssignedToUser(Long userId) {
        return taskRepository.findAll().stream()
                .filter(task -> task.getAssignedUser() != null && task.getAssignedUser().getId().equals(userId))
                .map(task -> new TaskDTO(task))
                .collect(Collectors.toList());
    }

    public List<Task> getAllTasksInGroup(Long groupId) {
        return taskRepository.findByGroupId(groupId);
    }
    
    //19/03
/*     public void deleteTask(Long id, User user) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            if (task.getAssignedUser().equals(user)) {
                taskRepository.delete(task);
            } else {
                throw new UnauthorizedException("No tienes permiso para eliminar esta tarea.");
            }
        } else {
            throw new TaskNotFoundException ("Tarea no encontrada.");
        }
    }
 */

 //19/03
    public void deleteTask(Long id, User user) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            // Verifica si el usuario autenticado es un administrador
            if (user.isAdmin()) {
                taskRepository.delete(task);
            } else {
                throw new UnauthorizedException("Sólo los administradores pueden eliminar tareas.");
            }
        } else {
            throw new TaskNotFoundException("Tarea no encontrada.");
        }
    }

    public Task convertToTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        //task.setAssignedUser(taskDTO.getAssignedUser());
        User assignedUser = userService.findById(taskDTO.getAssignedUserId());
        task.setAssignedUser(assignedUser);
        task.setUrgent(taskDTO.isUrgent());
        task.setCompleted(taskDTO.isCompleted());
        
        return task;
    }
}
