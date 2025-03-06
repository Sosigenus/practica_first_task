package practica.springstudentsz.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DTOclass {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String email;

    private String groupName;

}
