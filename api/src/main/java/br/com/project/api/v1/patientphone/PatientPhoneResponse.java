package br.com.project.api.v1.patientphone;

import br.com.project.domain.patientphone.PatientPhoneEntity;

public record PatientPhoneResponse(String id,
                                   String phone) {

    public static PatientPhoneResponse fromEntity(PatientPhoneEntity entity) {
        return new PatientPhoneResponse(
            entity.getId(),
            entity.getPhone());
    }
}
