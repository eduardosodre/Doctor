package br.com.project.api.v1.treatment;

import br.com.project.domain.treatment.TreatmentEntity;

public record TreatmentRequest(String doctorId,
                               String patientId,
                               String appointmentId,
                               String diagnosis,
                               String prescription,
                               String notes,
                               String outcome) {

    public TreatmentEntity toEntity() {
        final var entity = TreatmentEntity.create(
            doctorId,
            patientId,
            appointmentId,
            diagnosis,
            prescription,
            notes,
            outcome);
        return entity;
    }

    public TreatmentEntity toEntity(String id) {
        final var entity = TreatmentEntity.with(
            id,
            doctorId,
            patientId,
            appointmentId,
            diagnosis,
            prescription,
            notes,
            outcome, null, null, null);
        return entity;
    }

}
