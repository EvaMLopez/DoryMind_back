package dev.eva.dorymind.register;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.eva.dorymind.users.User;

@RestController
@RequestMapping("${api-endpoint}")
public class RegisterController {

    RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")    
    public ResponseEntity<User> registerUser(@RequestBody RegisterDTO registerDTO) {
        User user = registerService.registerUser(registerDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED); 
    }
}