package practica.springstudentsz.repository;
import practica.springstudentsz.model.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
    void deleteByEmail(String email);
    Student findStudentByEmail(String email);
}
