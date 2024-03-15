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

    public Task save(Task task) {
        return taskRepository.save(task);
    }
    
    // Método para crear una tarea
    public Task createTask(Task task, User user) {
        // Aquí puedes verificar los permisos del usuario
        // Por ejemplo, solo permitir la creación de tareas si el usuario tiene el rol de Admin
        if (user.getRole().getRoleName().equals("Admin")) {
            return taskRepository.save(task);
        } else {
            throw new UnauthorizedException("No tienes permiso para crear tareas.");
        }
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
    
    // Método para eliminar una tarea
    public void deleteTask(Long id, User user) {
        // Aquí puedes verificar los permisos del usuario
        // Por ejemplo, solo permitir la eliminación de tareas si el usuario es el creador de la tarea o tiene el rol de Admin
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            if (user.getRole().getRoleName().equals("Admin") || task.getAssignedUser().equals(user)) {
                taskRepository.delete(task);
            } else {
                throw new UnauthorizedException("No tienes permiso para eliminar esta tarea.");
            }
        } else {
            throw new TaskNotFoundException ("Tarea no encontrada.");
        }
    }

    // Métodos específicos para Task
    // ... implementar según sea necesario
}
