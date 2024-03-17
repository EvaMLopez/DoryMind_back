package dev.eva.dorymind.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import dev.eva.dorymind.roles.Role;
import dev.eva.dorymind.roles.RoleRepository;


@RequestMapping(path = "${api-endpoint}/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id); 
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user != null) {
            userService.delete(user);
        }
    }

    @PostMapping("/set-role")
    public User setRoleToUser(@RequestBody User user, @RequestParam String roleName) {
        Role role = roleRepository.findByName(roleName);
        return userService.setRoleToUser(user, role);
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user, @RequestParam String groupName) {
        return userService.registerUser(user, groupName);
    }

    @PostMapping("/add-member")
    public User addMemberToGroup(@AuthenticationPrincipal UserDetails userDetails, @RequestBody User newMember) {
        User currentUser = userService.findById(((User) userDetails).getId());
        return userService.addMemberToGroup(currentUser, newMember);
    }
}
