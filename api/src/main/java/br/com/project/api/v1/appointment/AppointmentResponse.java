package br.com.project.api.v1.appointment;

import br.com.project.domain.appointment.AppointmentEntity;
import java.time.LocalDateTime;

public record AppointmentResponse(String id,
                                  String doctorId,
                                  String patientId,
                                  LocalDateTime appointmentDate,
                                  String status) {

    public static AppointmentResponse fromEntity(AppointmentEntity entity) {
        return new AppointmentResponse(
            entity.getId(),
            entity.getDoctorId(),
            entity.getPatientId(),
            entity.getAppointmentDate(),
            entity.getStatus());
    }
}
