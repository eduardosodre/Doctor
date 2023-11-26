package br.com.project.api.v1.appointment;

import br.com.project.domain.appointment.AppointmentEntity;
import java.time.LocalDateTime;

public record AppointmentRequest(String doctorId,
                                 String patientId,
                                 LocalDateTime appointmentDate,
                                 String status) {

    public AppointmentEntity toEntity() {
        final var entity = AppointmentEntity.create(
            doctorId,
            patientId,
            appointmentDate,
            status);
        return entity;
    }

    public AppointmentEntity toEntity(String id) {
        final var entity = AppointmentEntity.with(
            id,
            doctorId,
            patientId,
            appointmentDate,
            status, null, null, null);
        return entity;
    }

}
