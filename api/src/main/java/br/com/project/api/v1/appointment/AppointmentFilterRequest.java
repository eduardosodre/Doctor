package br.com.project.api.v1.appointment;

import br.com.project.domain.appointment.AppointmentFilter;
import java.time.LocalDateTime;

public record AppointmentFilterRequest(String id,
                                       String doctorId,
                                       String patientId,
                                       LocalDateTime initialAppointmentDate,
                                       LocalDateTime finalAppointmentDate,
                                       String status) {

    public AppointmentFilter toEntity() {
        return AppointmentFilter.create(id,
            doctorId,
            patientId,
            initialAppointmentDate,
            finalAppointmentDate,
            status);
    }

}
