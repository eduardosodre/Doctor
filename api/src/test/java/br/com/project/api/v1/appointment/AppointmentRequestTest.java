package br.com.project.api.v1.appointment;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppointmentRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedDoctorId = "6f58f94d-a031-4b12-9709-773d799a86b0";
        final var expectedPatientId = "cbcc6381-9a2b-4831-801b-6281b434213b";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusHtadl";
        final var actualAppointmentRequest = new AppointmentRequest(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);

        final var entity = actualAppointmentRequest.toEntity();

        Assertions.assertEquals(expectedDoctorId, entity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, entity.getPatientId());
        Assertions.assertEquals(expectedAppointmentDate, entity.getAppointmentDate());
        Assertions.assertEquals(expectedStatus, entity.getStatus());
    }

    @Test
    void shouldToEntityWithId() {
        final var expectedId = "3753b520-82b5-4633-9831-5e84bb8f3c77";
        final var expectedDoctorId = "d821e22b-f1fb-47b3-9c6a-f7374e8046d1";
        final var expectedPatientId = "a0e176b3-255f-4646-96f7-a36afc99e36f";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusOFBZA";
        final var actualAppointmentRequest = new AppointmentRequest(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);

        final var entity = actualAppointmentRequest.toEntity(expectedId);

        Assertions.assertEquals(expectedId, entity.getId());
        Assertions.assertEquals(expectedDoctorId, entity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, entity.getPatientId());
        Assertions.assertEquals(expectedAppointmentDate, entity.getAppointmentDate());
        Assertions.assertEquals(expectedStatus, entity.getStatus());
    }
}
