package dev.eva.dorymind.groups;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository <Group, Long> {
    Group findByGroupName(String groupName);    
}
