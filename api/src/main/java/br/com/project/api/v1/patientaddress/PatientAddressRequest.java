package br.com.project.api.v1.patientaddress;

import br.com.project.domain.patient.PatientEntity;
import br.com.project.domain.patientaddress.PatientAddressEntity;
import java.time.LocalDateTime;

public record PatientAddressRequest(String id,
                                    String street,
                                    String number,
                                    String neighborhood,
                                    String city,
                                    String state,
                                    String country) {

    public PatientAddressEntity toEntity(PatientEntity patient) {
        if (id == null) {
            return PatientAddressEntity.create(street,
                number,
                neighborhood,
                city,
                state,
                country,
                patient);
        }
        return PatientAddressEntity.with(id,
            street,
            number,
            neighborhood,
            city,
            state,
            country,
            patient,
            LocalDateTime.now(),
            null,
            null);
    }

}
