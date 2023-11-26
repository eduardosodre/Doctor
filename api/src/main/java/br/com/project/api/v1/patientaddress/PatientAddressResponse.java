package br.com.project.api.v1.patientaddress;

import br.com.project.domain.patientaddress.PatientAddressEntity;

public record PatientAddressResponse(String id,
                                     String street,
                                     String number,
                                     String neighborhood,
                                     String city,
                                     String state,
                                     String country) {

    public static PatientAddressResponse fromEntity(PatientAddressEntity entity) {
        return new PatientAddressResponse(
            entity.getId(),
            entity.getStreet(),
            entity.getNumber(),
            entity.getNeighborhood(),
            entity.getCity(),
            entity.getState(),
            entity.getCountry());
    }
}
