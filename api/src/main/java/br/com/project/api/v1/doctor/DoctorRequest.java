package br.com.project.api.v1.doctor;

import br.com.project.domain.doctor.DoctorEntity;

public record DoctorRequest(String crm,
                            String name,
                            String specialty) {

    public DoctorEntity toEntity() {
        final var entity = DoctorEntity.create(
            crm,
            name,
            specialty);
        return entity;
    }

    public DoctorEntity toEntity(String id) {
        final var entity = DoctorEntity.with(
            id,
            crm,
            name,
            specialty, null, null, null);
        return entity;
    }

}
