package dev.eva.dorymind.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.eva.dorymind.groups.Group;
import dev.eva.dorymind.groups.GroupService;
import dev.eva.dorymind.roles.Role;
import dev.eva.dorymind.roles.RoleRepository;
import dev.eva.dorymind.tasks.UnauthorizedException;
import io.micrometer.common.lang.NonNull;

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

    public User findByUsername(String username) {
    return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public User save(@NonNull User type) {
        User newUser = new User();

        userRepository.save(newUser);
        return newUser;
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void setRoleToUser(User user) {
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRole(adminRole);        
    }

    public User addMemberToGroup(User currentUser, User newMember) {
        if (!currentUser.isAdmin()) {
            throw new UnauthorizedException("No tienes permisos para añadir miembros al grupo.");
        }

        Group group = currentUser.getGroup();
        groupService.addUserToGroup(newMember, group);
        return userRepository.save(newMember);
    }

    public Long getGroupIdByUserId(Long userId) {
        return userRepository.findGroupIdByUserId(userId);
    }    
}
