package practica.springstudentsz.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

//import lombok.NonNull;

@Data
@Builder
/*
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
Hashcode
Equals
*/
public class Student {
    // ТИПЫ ДАННЫХ
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    //@NonNull // ПОЧИТАТЬ

    private String email;
    private int age;
}