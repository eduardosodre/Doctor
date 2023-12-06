package br.com.project.domain.appointment;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppointmentEntityTest {

    @Test
    void shouldCreateNewAppointmentEntity() {
        final var expectedDoctorId = "93e34bd5-c2c5-4c62-af27-32b96b888ffa";
        final var expectedPatientId = "fcb1105a-97de-4515-9186-13e199289246";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusnhS8K";

        final var actualAppointmentEntity = AppointmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);
        Assertions.assertNotNull(actualAppointmentEntity);
        Assertions.assertNotNull(actualAppointmentEntity.getId());
        Assertions.assertNotNull(actualAppointmentEntity.getCreatedAt());
        Assertions.assertNull(actualAppointmentEntity.getUpdatedAt());
        Assertions.assertNull(actualAppointmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualAppointmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualAppointmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentDate, actualAppointmentEntity.getAppointmentDate());
        Assertions.assertEquals(expectedStatus, actualAppointmentEntity.getStatus());
    }
 
    @Test
    void shouldCreateNewAppointmentEntityAndUpdate() {
        final var expectedDoctorId = "45f8e603-ba4e-4a29-9c7b-da393fb311a0";
        final var expectedPatientId = "a85ef41b-2476-4c14-9d4c-eef00ef8733d";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string status4RD01";

        final var actualAppointmentEntity = AppointmentEntity.create("3dedd1d0-04d4-4eb0-8475-320ca177dfb6", "d43eed4c-45bc-4ddd-87a5-33c14e38ea8d",
            LocalDateTime.now(), "string statusNkHDv");
        final var id = actualAppointmentEntity.getId();

        actualAppointmentEntity.update(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);

        Assertions.assertNotNull(actualAppointmentEntity);
        Assertions.assertEquals(id, actualAppointmentEntity.getId());
        Assertions.assertNotNull(actualAppointmentEntity.getCreatedAt());
        Assertions.assertNotNull(actualAppointmentEntity.getUpdatedAt());
        Assertions.assertNull(actualAppointmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualAppointmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualAppointmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentDate, actualAppointmentEntity.getAppointmentDate());
        Assertions.assertEquals(expectedStatus, actualAppointmentEntity.getStatus());
    }

    @Test
    void shouldCopyWithAppointmentEntity() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string id7NTF1";
        final var expectedDoctorId = "0daa4164-cfe6-4541-9f0c-e7a581954962";
        final var expectedPatientId = "c4a760de-8d06-4686-b0f5-c1e56da6f974";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusLD660";

        final var actualAppointmentEntity = AppointmentEntity.with(expectedId, expectedDoctorId, expectedPatientId, expectedAppointmentDate,
            expectedStatus, expectedCreated, expectedUpdated, expectedDeleted);
        Assertions.assertNotNull(actualAppointmentEntity);
        Assertions.assertNotNull(actualAppointmentEntity.getId());
        Assertions.assertNotNull(actualAppointmentEntity.getCreatedAt());
        Assertions.assertNotNull(actualAppointmentEntity.getUpdatedAt());
        Assertions.assertNotNull(actualAppointmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualAppointmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualAppointmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentDate, actualAppointmentEntity.getAppointmentDate());
        Assertions.assertEquals(expectedStatus, actualAppointmentEntity.getStatus());
    }

    @Test
    void shouldCopyWithAppointmentEntityFromObject() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idutjYt";
        final var expectedDoctorId = "5f07db5b-6a2a-4d19-b9e7-7b5f219133b7";
        final var expectedPatientId = "4a853c3b-9a6d-4bc9-8786-37abbb2bd051";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusBgzYR";

        final var actualAppointmentEntity = AppointmentEntity.with(expectedId, expectedDoctorId, expectedPatientId, expectedAppointmentDate,
            expectedStatus, expectedCreated, expectedUpdated, expectedDeleted);
        final var copyFromAppointmentEntity = AppointmentEntity.with(actualAppointmentEntity);
        Assertions.assertNotNull(actualAppointmentEntity);
        Assertions.assertNotNull(actualAppointmentEntity.getId());
        Assertions.assertNotNull(actualAppointmentEntity.getCreatedAt());
        Assertions.assertNotNull(actualAppointmentEntity.getUpdatedAt());
        Assertions.assertNotNull(actualAppointmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualAppointmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualAppointmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentDate, actualAppointmentEntity.getAppointmentDate());
        Assertions.assertEquals(expectedStatus, actualAppointmentEntity.getStatus());
        Assertions.assertEquals(actualAppointmentEntity.getDoctorId(), copyFromAppointmentEntity.getDoctorId());
        Assertions.assertEquals(actualAppointmentEntity.getPatientId(), copyFromAppointmentEntity.getPatientId());
        Assertions.assertEquals(actualAppointmentEntity.getAppointmentDate(), copyFromAppointmentEntity.getAppointmentDate());
        Assertions.assertEquals(actualAppointmentEntity.getStatus(), copyFromAppointmentEntity.getStatus());
        Assertions.assertEquals(actualAppointmentEntity.getCreatedAt(), copyFromAppointmentEntity.getCreatedAt());
        Assertions.assertEquals(actualAppointmentEntity.getUpdatedAt(), copyFromAppointmentEntity.getUpdatedAt());
        Assertions.assertEquals(actualAppointmentEntity.getDeletedAt(), copyFromAppointmentEntity.getDeletedAt());
    }

    @Test
    void shouldCreateNewAppointmentEntityAndDelete() {
        final var expectedDoctorId = "d2e1eb1a-5342-4c00-92c2-5f7c20b8ea32";
        final var expectedPatientId = "889fadf1-a06d-4e15-81af-446e2089b522";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusafbXY";

        final var actualAppointmentEntity = AppointmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);
        actualAppointmentEntity.delete();
        Assertions.assertNotNull(actualAppointmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualAppointmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualAppointmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentDate, actualAppointmentEntity.getAppointmentDate());
        Assertions.assertEquals(expectedStatus, actualAppointmentEntity.getStatus());
    }
}
