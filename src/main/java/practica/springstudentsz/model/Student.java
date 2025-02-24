package practica.springstudentsz.model;
import practica.springstudentsz.model.Group;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.context.annotation.DeferredImportSelector;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    //@Column(name = "firstName")
    private String firstName;
    //@Column(name = "lastName")
    private String lastName;
    //@Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;
    @Column(unique = true)
    private String email;
    @Transient
    private int age;

    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
