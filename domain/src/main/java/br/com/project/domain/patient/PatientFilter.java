package br.com.project.domain.patient;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatientFilter {

    private String id;
    private String name;
    private LocalDate initialBirthdate;
    private LocalDate finalBirthdate;
    private String gender;

    public static PatientFilter create(final String id,
                                       final String name,
                                       final LocalDate initialBirthdate,
                                       final LocalDate finalBirthdate,
                                       final String gender) {
        return new PatientFilter(id,
            name,
            initialBirthdate,
            finalBirthdate,
            gender);
    }
}
