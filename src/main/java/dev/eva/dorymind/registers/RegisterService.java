package dev.eva.dorymind.registers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dev.eva.dorymind.facades.encryptations.EncoderFacade;
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

    @Autowired
    EncoderFacade encoderFacade;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 

    public User registerUser(RegisterDTO registerDTO) {

        //ULTIMO AÑADIDO
        String groupName = registerDTO.getGroupName();
        if (groupName == null || groupName.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del grupo no puede ser nulo ni estar vacío");
        }

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

        // Primero, codificamos la contraseña usando Base64
        String passwordBase64Encoded = encoderFacade.encode("base64", registerDTO.getPassword());
        System.out.println("Contraseña codificada en Base64: " + passwordBase64Encoded);

        // Luego, decodificamos la contraseña de Base64 y la codificamos usando bcrypt
        String passwordDecoded = encoderFacade.decode("base64", passwordBase64Encoded);
        String passwordBCryptEncoded = passwordEncoder/*  */.encode(passwordDecoded);
        user.setPassword(passwordBCryptEncoded);

        user.setEmail(registerDTO.getEmail());
        user.setGroup(group);
        user.setRole(adminRole);

        return userRepository.save(user);
/* 
        // Crea nuevo usuario
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setEmail(registerDTO.getEmail());
        user.setGroup(group);
        user.setRole(adminRole);

        return userRepository.save(user); */

        
    }    
}
