package dev.eva.dorymind.users;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dev.eva.dorymind.groups.Group;
import dev.eva.dorymind.roles.Role;
import dev.eva.dorymind.tasks.Task;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="users")
public class User {
    
    public User() {
    }
    
    public User(Long id, String username, String password, String email, Group group) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.group = group;
        this.roles = new HashSet<>();
    }

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

    //último añadido:@OneToMany
    @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    
    private Set<Role> roles = new HashSet<>();

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

    // public void setGroup(Group group) {
    //     this.group = group;
    //     if (group != null && !group.getUsers().contains(this)) {
    //         group.getUsers().add(this);
    //     }
    // }

    // public void addUserToGroup(User userToAdd) {
    //     if (group != null && userToAdd != null) {
    //         if (!group.getUsers().contains(userToAdd)) {
    //             group.addUser(userToAdd);
    //             userToAdd.setGroup(group);
    //         }
    //     }
    // }


    // creo que no es necesior ** probar ***
/*     public void addUserToGroup(Group group) {
        if (group != null && !group.getUsers().contains(this)) {
            group.getUsers().add(this);
            this.group = group;
        }
    }

    public void removeUserFromGroup() {
        if (group != null) {
            group.getUsers().remove(this);
            this.group = null;
        }
    } */

    public boolean isAdmin() {
        return hasRole("ROLE_ADMIN");
    }
    
    public boolean isUser() {
        return hasRole("ROLE_USER");
    }
    
    private boolean hasRole(String roleName) {
        return roles.stream().anyMatch(role -> role.getName().equals(roleName));
    }
    
    public void assignRole(Role role) {
        if (!roles.contains(role)) {
            roles.add(role);
            role.getUsers().add(this);
        }
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
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


    
        
    
    

