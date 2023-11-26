package br.com.project.api.v1.patientphone;

import br.com.project.domain.patient.PatientEntity;
import br.com.project.domain.patientphone.PatientPhoneEntity;
import java.time.LocalDateTime;

public record PatientPhoneRequest(String id,
                                  String phone) {

    public PatientPhoneEntity toEntity(PatientEntity patient) {
        if (id == null) {
            return PatientPhoneEntity.create(phone,
                patient);
        }
        return PatientPhoneEntity.with(id,
            phone,
            patient,
            LocalDateTime.now(),
            null,
            null);
    }

}
