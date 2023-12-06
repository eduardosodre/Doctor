package br.com.project.api.v1.appointment;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppointmentFilterRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedId = "string idfz5lK";
        final var expectedDoctorId = "ee224cf5-7715-4045-b421-39faeb525af9";
        final var expectedPatientId = "ee6b28d7-594e-4fe5-88e6-3ea314f7e1de";
        final var expectedInitialAppointmentDate = LocalDateTime.now();
        final var expectedFinalAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusBYJZB";
        final var actualAppointmentFilterRequest = new AppointmentFilterRequest(expectedId, expectedDoctorId, expectedPatientId,
            expectedInitialAppointmentDate, expectedFinalAppointmentDate, expectedStatus);

        final var entity = actualAppointmentFilterRequest.toEntity();

        Assertions.assertEquals(expectedId, entity.getId());
        Assertions.assertEquals(expectedDoctorId, entity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, entity.getPatientId());
        Assertions.assertEquals(expectedInitialAppointmentDate, entity.getInitialAppointmentDate());
        Assertions.assertEquals(expectedFinalAppointmentDate, entity.getFinalAppointmentDate());
        Assertions.assertEquals(expectedStatus, entity.getStatus());
    }

}
