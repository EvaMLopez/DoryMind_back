package dev.eva.dorymind.register;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.eva.dorymind.groups.Group;
import dev.eva.dorymind.groups.GroupRepository;
import dev.eva.dorymind.roles.Role;
import dev.eva.dorymind.roles.RoleRepository;
import dev.eva.dorymind.users.User;
import dev.eva.dorymind.users.UserRepository;


@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

    public User registerUser(RegisterDTO registerDTO) {
        Group group = groupRepository.findByGroupName(registerDTO.getGroupName());
        if (group == null) {
            group = new Group();
            group.setGroupName(registerDTO.getGroupName());
            group = groupRepository.save(group);
        }

        // Busca rol de Admin
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

        // Crea nuevo usuario
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setGroup(group);
        user.setRole(adminRole);

        return userRepository.save(user);
    }    
}
