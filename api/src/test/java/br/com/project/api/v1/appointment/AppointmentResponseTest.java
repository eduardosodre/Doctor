package br.com.project.api.v1.appointment;

import br.com.project.domain.appointment.AppointmentEntity;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppointmentResponseTest {

    @Test
    void shouldToEntity() {
        final var expectedDoctorId = "6e7cf9b9-1116-40bc-a7d7-573af0bc4eca";
        final var expectedPatientId = "7a13678a-9aff-44c6-b5c9-2544493e8cbd";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string status0vlqN";

        final var actualAppointmentEntity = AppointmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);
        final var response = AppointmentResponse.fromEntity(actualAppointmentEntity);

        Assertions.assertEquals(actualAppointmentEntity.getId(), response.id());
        Assertions.assertEquals(expectedDoctorId, response.doctorId());
        Assertions.assertEquals(expectedPatientId, response.patientId());
        Assertions.assertEquals(expectedAppointmentDate, response.appointmentDate());
        Assertions.assertEquals(expectedStatus, response.status());
    }

}
