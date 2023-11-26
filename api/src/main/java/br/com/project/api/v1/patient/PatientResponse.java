package br.com.project.api.v1.patient;

import br.com.project.api.v1.patientaddress.PatientAddressResponse;
import br.com.project.api.v1.patientemail.PatientEmailResponse;
import br.com.project.api.v1.patientphone.PatientPhoneResponse;
import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import java.util.List;

public record PatientResponse(String id,
                              String name,
                              LocalDate birthdate,
                              String gender,
                              List<PatientPhoneResponse> phones,
                              List<PatientAddressResponse> addresses,
                              List<PatientEmailResponse> emails) {

    public static PatientResponse fromEntity(PatientEntity entity) {
        return new PatientResponse(
            entity.getId(),
            entity.getName(),
            entity.getBirthdate(),
            entity.getGender(),
            entity.getPhones().stream().map(PatientPhoneResponse::fromEntity).toList(),
            entity.getAddresses().stream().map(PatientAddressResponse::fromEntity).toList(),
            entity.getEmails().stream().map(PatientEmailResponse::fromEntity).toList());
    }
}
