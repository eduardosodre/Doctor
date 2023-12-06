package br.com.project.domain.patientphone;

import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientPhoneEntityTest {

    @Test
    void shouldCreateNewPatientPhoneEntity() {
        final var expectedPhone = "string phoneBBe33";
        final var expectedPatient = PatientEntity.create("string name6z5Yo2", LocalDate.now(), "string gendersvH3J");

        final var actualPatientPhoneEntity = PatientPhoneEntity.create(expectedPhone, expectedPatient);
        Assertions.assertNotNull(actualPatientPhoneEntity);
        Assertions.assertNotNull(actualPatientPhoneEntity.getId());
        Assertions.assertNotNull(actualPatientPhoneEntity.getCreatedAt());
        Assertions.assertNull(actualPatientPhoneEntity.getUpdatedAt());
        Assertions.assertNull(actualPatientPhoneEntity.getDeletedAt());
        Assertions.assertEquals(expectedPhone, actualPatientPhoneEntity.getPhone());
        Assertions.assertEquals(expectedPatient, actualPatientPhoneEntity.getPatient());
    }

    @Test
    void shouldCreateNewPatientPhoneEntityAndUpdate() {
        final var expectedPhone = "string phone8UwHo";
        final var expectedPatient = PatientEntity.create("string nameQSqzTk", LocalDate.now(), "string genderKCoXv");

        final var actualPatientPhoneEntity = PatientPhoneEntity.create("string phoneQu0S7",
            PatientEntity.create("string namesrNwhl", LocalDate.now(), "string gender4cD91"));
        final var id = actualPatientPhoneEntity.getId();

        actualPatientPhoneEntity.update(expectedPhone);

        Assertions.assertNotNull(actualPatientPhoneEntity);
        Assertions.assertEquals(id, actualPatientPhoneEntity.getId());
        Assertions.assertNotNull(actualPatientPhoneEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientPhoneEntity.getUpdatedAt());
        Assertions.assertNull(actualPatientPhoneEntity.getDeletedAt());
        Assertions.assertEquals(expectedPhone, actualPatientPhoneEntity.getPhone());
    }

    @Test
    void shouldCopyWithPatientPhoneEntity() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idzPPxX";
        final var expectedPhone = "string phonewkAZ1";
        final var expectedPatient = PatientEntity.create("string nameTwbcmz", LocalDate.now(), "string genderNTFWQ");

        final var actualPatientPhoneEntity = PatientPhoneEntity.with(expectedId, expectedPhone, expectedPatient, expectedCreated, expectedUpdated,
            expectedDeleted);
        Assertions.assertNotNull(actualPatientPhoneEntity);
        Assertions.assertNotNull(actualPatientPhoneEntity.getId());
        Assertions.assertNotNull(actualPatientPhoneEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientPhoneEntity.getUpdatedAt());
        Assertions.assertNotNull(actualPatientPhoneEntity.getDeletedAt());
        Assertions.assertEquals(expectedPhone, actualPatientPhoneEntity.getPhone());
        Assertions.assertEquals(expectedPatient, actualPatientPhoneEntity.getPatient());
    }

    @Test
    void shouldCopyWithPatientPhoneEntityFromObject() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idPFymx";
        final var expectedPhone = "string phone6Brzr";
        final var expectedPatient = PatientEntity.create("string namegTMtJe", LocalDate.now(), "string genderdxmtA");

        final var actualPatientPhoneEntity = PatientPhoneEntity.with(expectedId, expectedPhone, expectedPatient, expectedCreated, expectedUpdated,
            expectedDeleted);
        final var copyFromPatientPhoneEntity = PatientPhoneEntity.with(actualPatientPhoneEntity);
        Assertions.assertNotNull(actualPatientPhoneEntity);
        Assertions.assertNotNull(actualPatientPhoneEntity.getId());
        Assertions.assertNotNull(actualPatientPhoneEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientPhoneEntity.getUpdatedAt());
        Assertions.assertNotNull(actualPatientPhoneEntity.getDeletedAt());
        Assertions.assertEquals(expectedPhone, actualPatientPhoneEntity.getPhone());
        Assertions.assertEquals(expectedPatient, actualPatientPhoneEntity.getPatient());
        Assertions.assertEquals(actualPatientPhoneEntity.getPhone(), copyFromPatientPhoneEntity.getPhone());
        Assertions.assertEquals(actualPatientPhoneEntity.getPatient(), copyFromPatientPhoneEntity.getPatient());
        Assertions.assertEquals(actualPatientPhoneEntity.getCreatedAt(), copyFromPatientPhoneEntity.getCreatedAt());
        Assertions.assertEquals(actualPatientPhoneEntity.getUpdatedAt(), copyFromPatientPhoneEntity.getUpdatedAt());
        Assertions.assertEquals(actualPatientPhoneEntity.getDeletedAt(), copyFromPatientPhoneEntity.getDeletedAt());
    }

    @Test
    void shouldCreateNewPatientPhoneEntityAndDelete() {
        final var expectedPhone = "string phoneVNsRj";
        final var expectedPatient = PatientEntity.create("string name7X3C6w", LocalDate.now(), "string genderYlXl3");

        final var actualPatientPhoneEntity = PatientPhoneEntity.create(expectedPhone, expectedPatient);
        actualPatientPhoneEntity.delete();
        Assertions.assertNotNull(actualPatientPhoneEntity.getDeletedAt());
        Assertions.assertEquals(expectedPhone, actualPatientPhoneEntity.getPhone());
        Assertions.assertEquals(expectedPatient, actualPatientPhoneEntity.getPatient());
    }
}
