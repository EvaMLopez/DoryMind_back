package dev.eva.dorymind.users;

import java.util.ArrayList;
import java.util.List;
import dev.eva.dorymind.groups.Group;
import dev.eva.dorymind.roles.Role;
import dev.eva.dorymind.tasks.Task;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    // ** un Role puede tener muchos User, pero cada User tiene un único Role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
        
    public User() {
    }
    
    public User(Long id, String username, String password, String email, Group group) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.group = group;    
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
    this.group = group;  
    }

    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }
    
    public boolean isUser() {
        return hasRole("ROLE_USER");
    }
    
/*     private boolean hasRole(String roleName) {
        return roles.stream().anyMatch(role -> role.getRoleName().equals(roleName));
    } */

    private boolean hasRole(String roleName) {
        return this.role.getRoleName().equals(roleName);
    }
    
    public void assignRole(Role role) {
        this.role = role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void editProfile(String newUsername, String newPassword, String newEmail) {
        if (newUsername != null && !newUsername.isEmpty()) {
            this.username = newUsername;
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            this.password = newPassword;
        }
        if (newEmail != null && !newEmail.isEmpty()) {
            this.email = newEmail;
        }
    }

}


    
        
    
    

