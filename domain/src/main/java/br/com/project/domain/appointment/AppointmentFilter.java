package br.com.project.domain.appointment;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppointmentFilter {

    private String id;
    private String doctorId;
    private String patientId;
    private LocalDateTime initialAppointmentDate;
    private LocalDateTime finalAppointmentDate;
    private String status;

    public static AppointmentFilter create(final String id,
                                           final String doctorId,
                                           final String patientId,
                                           final LocalDateTime initialAppointmentDate,
                                           final LocalDateTime finalAppointmentDate,
                                           final String status) {
        return new AppointmentFilter(id,
            doctorId,
            patientId,
            initialAppointmentDate,
            finalAppointmentDate,
            status);
    }
}
