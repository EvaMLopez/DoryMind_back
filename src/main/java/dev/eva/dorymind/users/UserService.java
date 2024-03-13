package dev.eva.dorymind.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import dev.eva.dorymind.roles.Role;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

        public List<User> findAll() {
        return userRepository.findAll();
    }

        public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

/*     public User save(@NonNull User type) {
        User newUser = new User();

        repository.save(newUser);
        return newUser;
    } */

/*        public User save(User user) {
        return userRepository.save(user);
    } */


/*     public User save(User user) {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new InvalidDataException("Username cannot be null or empty");
        }
        // ... otras validaciones
        return userRepository.save(user);
    } */

        public User update(User user) {
        return userRepository.save(user);
    }

        public void delete(User user) {
        userRepository.delete(user);
    }

        public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

        public User addRoleToEntity(User user, Role role) {
        user.getRoles().add(role);
        return userRepository.save(user);
    }


    // Métodos específicos para User

    //agrega un rol a la lista de roles del usuario y guarda el usuario actualizado en la base de datos.
    public User addRoleToUser(User user, Role role) {
        user.getRoles().add(role);
        return userRepository.save(user);
    }
  
    // ... otros métodos específicos para User
}