package dev.eva.dorymind.groups;

import java.util.ArrayList;
import java.util.List;

import dev.eva.dorymind.tasks.Task;
import dev.eva.dorymind.users.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {

    public Group(Long id, String groupName, List<User> users, List<Task> tasks) {
        this.id = id;
        this.groupName = groupName;
        this.users = users != null ? users : new ArrayList<>();
        this.tasks = tasks != null ? tasks : new ArrayList<>();
    }

    public Group() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String groupName;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

/*     @OneToMany(mappedBy = "assignedUser", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>(); */

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }    

    public void addUser (User user) {
        users.add(user);
        user.setGroup(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setGroup(null);
    }
}
