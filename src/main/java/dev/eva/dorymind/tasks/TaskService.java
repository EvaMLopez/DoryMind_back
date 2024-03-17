package dev.eva.dorymind.tasks;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.eva.dorymind.users.User;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
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
    
    public List<Task> getTasksAssignedToUser(Long userId) {
        return taskRepository.findByAssignedUser(userId);
    }
    
    public List<Task> getAllTasksInGroup(Long groupId) {
        return taskRepository.findByGroupId(groupId);
    }
    
    public void deleteTask(Long id, User user) {
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

}
