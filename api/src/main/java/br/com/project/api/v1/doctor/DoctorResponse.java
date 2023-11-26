package br.com.project.api.v1.doctor;

import br.com.project.domain.doctor.DoctorEntity;

public record DoctorResponse(String id,
                             String crm,
                             String name,
                             String specialty) {

    public static DoctorResponse fromEntity(DoctorEntity entity) {
        return new DoctorResponse(
            entity.getId(),
            entity.getCrm(),
            entity.getName(),
            entity.getSpecialty());
    }
}
