package br.com.project.domain.appointment;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppointmentEntityTest {
 
    @Test
    void shouldCreateNewAppointmentEntity() {
        final var expectedDoctorId = "8b9effba-671f-4504-8ed9-a8bc71a4201f";
        final var expectedPatientId = "1435bcb0-83c6-406f-a8b0-4ccb830ea2b6";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusyIIJ6";

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
        final var expectedDoctorId = "c3c93371-41d4-4244-9133-7b5d50075e4a";
        final var expectedPatientId = "83ed00a6-6929-4532-8614-c69007ba38db";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusdd0Rd";

        final var actualAppointmentEntity = AppointmentEntity.create("d5b5679b-d916-46c6-a1b8-bb425e04ac49", "a37d1d0a-2fcd-4d70-aec9-40f580087e95",
            LocalDateTime.now(), "string status8PFEZ");
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
        final var expectedId = "string iddiSxi";
        final var expectedDoctorId = "4a870417-4be9-400c-b94d-ae49dcbc3a18";
        final var expectedPatientId = "3c6de221-fc70-4563-b443-bcd44e9fa00a";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string status98Lsk";

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
        final var expectedId = "string idWTcrC";
        final var expectedDoctorId = "53f506dc-6f0c-4829-bbab-b4630ee583ba";
        final var expectedPatientId = "07410e10-c4a3-4a50-a35f-a2c467d2d371";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusMgA2x";

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
        final var expectedDoctorId = "82d78ae3-1e12-488a-8144-47bd88ec18d5";
        final var expectedPatientId = "ffd724d7-02c4-4abb-9173-46c869e04036";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusuS3xL";

        final var actualAppointmentEntity = AppointmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);
        actualAppointmentEntity.delete();
        Assertions.assertNotNull(actualAppointmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualAppointmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualAppointmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentDate, actualAppointmentEntity.getAppointmentDate());
        Assertions.assertEquals(expectedStatus, actualAppointmentEntity.getStatus());
    }
}
