package dev.eva.dorymind.registers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;


import dev.eva.dorymind.users.User;

@RestController
@RequestMapping("${api-endpoint}")
public class RegisterController {

    RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }
/* 
    @PostMapping("/register")    
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO) {
        User user = registerService.registerUser(registerDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED); 
    } */

    // ** ULTIMO AÑADIDO **
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {
        // Verifica si el nombre del grupo está presente y no es nulo ni vacío
        if (registerDTO.getGroupName() == null || registerDTO.getGroupName().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                   .contentType(MediaType.APPLICATION_JSON)
                   .body("El nombre del grupo no puede ser nulo ni estar vacío.");
        }
        
        try {
            User user = registerService.registerUser(registerDTO);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            // Maneja otras excepciones si es necesario
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}