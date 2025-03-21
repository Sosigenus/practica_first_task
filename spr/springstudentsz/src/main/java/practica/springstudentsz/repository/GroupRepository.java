package practica.springstudentsz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practica.springstudentsz.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByName(String name);
}
