package br.com.project.api.v1.patient;

import br.com.project.api.v1.patientaddress.PatientAddressRequest;
import br.com.project.api.v1.patientemail.PatientEmailRequest;
import br.com.project.api.v1.patientphone.PatientPhoneRequest;
import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record PatientRequest(String name,
                             LocalDate birthdate,
                             String gender,
                             List<PatientPhoneRequest> phones,
                             List<PatientAddressRequest> addresses,
                             List<PatientEmailRequest> emails) {

    public PatientEntity toEntity() {
        final var entity = PatientEntity.create(
            name,
            birthdate,
            gender);
        entity.setPhones(phones.stream().map(map -> map.toEntity(entity)).collect(Collectors.toList()));
        ;
        entity.setAddresses(addresses.stream().map(map -> map.toEntity(entity)).collect(Collectors.toList()));
        ;
        entity.setEmails(emails.stream().map(map -> map.toEntity(entity)).collect(Collectors.toList()));
        ;
        return entity;
    }

    public PatientEntity toEntity(String id) {
        final var entity = PatientEntity.with(
            id,
            name,
            birthdate,
            gender, null, null, null);
        entity.setPhones(phones.stream().map(map -> map.toEntity(entity)).collect(Collectors.toList()));
        entity.setAddresses(addresses.stream().map(map -> map.toEntity(entity)).collect(Collectors.toList()));
        entity.setEmails(emails.stream().map(map -> map.toEntity(entity)).collect(Collectors.toList()));
        return entity;
    }

}
