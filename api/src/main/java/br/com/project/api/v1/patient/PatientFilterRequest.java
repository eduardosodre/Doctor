package br.com.project.api.v1.patient;

import br.com.project.domain.patient.PatientFilter;
import java.time.LocalDate;

public record PatientFilterRequest(String id,
                                   String name,
                                   LocalDate initialBirthdate,
                                   LocalDate finalBirthdate,
                                   String gender) {

    public PatientFilter toEntity() {
        return PatientFilter.create(id,
            name,
            initialBirthdate,
            finalBirthdate,
            gender);
    }

}
