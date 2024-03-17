package dev.eva.dorymind.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.eva.dorymind.groups.Group;
import dev.eva.dorymind.groups.GroupService;
import dev.eva.dorymind.roles.Role;
import dev.eva.dorymind.roles.RoleRepository;
import dev.eva.dorymind.tasks.UnauthorizedException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private RoleRepository roleRepository;
 
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    //establece el rol de un usuario:
    public User setRoleToUser(User user, Role role) {
        user.setRole(role);
        return userRepository.save(user);
    }

    // registra un nuevo usuario y crea un grupo
    public User registerUser(User user, String groupName) {
        Group existingGroup = groupService.findByName(groupName);
        if (existingGroup != null) {
            throw new GroupAlreadyExistsException("El grupo " + groupName + " ya existe. Por favor, elige un nombre diferente para tu grupo.");
        }
    
        Group group = new Group();
        group.setGroupName(groupName);
        group = groupService.save(group);
    
        Role adminRole = roleRepository.findByName("Admin");
        user.setRole(adminRole);
    
        user.setGroup(group);
    
        group.addUser(user);
    
        return userRepository.save(user);
    }    
  
       // añade un miembro al grupo del usuario con Rol Admin
    public User addMemberToGroup(User currentUser, User newMember) {
        // Verifica si el usuario actual tiene el rol de Admin
        if (!currentUser.getRole().getname().equals("Admin")) {
            throw new UnauthorizedException("No tienes permiso para añadir miembros al grupo.");
        }

        // Utiliza el grupo del usuario con Rol Admin
        Group group = currentUser.getGroup();

        groupService.addUserToGroup(newMember, group);

        return userRepository.save(newMember);
    }

    // busca el id del grupo al que pertenece el usuario
    public Long getGroupIdByUserId(Long userId) {
        return userRepository.findGroupIdByUserId(userId);
    }    

}
