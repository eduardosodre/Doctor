package br.com.project.api.v1.patientemail;

import br.com.project.domain.patient.PatientEntity;
import br.com.project.domain.patientemail.PatientEmailEntity;
import java.time.LocalDateTime;

public record PatientEmailRequest(String id,
                                  String email) {

    public PatientEmailEntity toEntity(PatientEntity patient) {
        if (id == null) {
            return PatientEmailEntity.create(email,
                patient);
        }
        return PatientEmailEntity.with(id,
            email,
            patient,
            LocalDateTime.now(),
            null,
            null);
    }

}
