package dev.eva.dorymind.groups;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.eva.dorymind.users.User;

@Service
public class GroupService {
    
    @Autowired
    private GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public Group findByName(String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

    public Group update(Group group) {
        return groupRepository.save(group);
    }
    
    public void delete(Group group) {
        groupRepository.delete(group);
    }

    public void deleteById(Long id) {
        groupRepository.deleteById(id);
    }

    // Métodos específicos para Group
    public void addUserToGroup(User user, Group group) {
        group.getUsers().add(user);
        user.setGroup(group);
        groupRepository.save(group);
    }

    // ... otros métodos específicos para Group
    
}
