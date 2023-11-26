package br.com.project.domain.patientphone;

import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientPhoneEntityTest {
 
    @Test
    void shouldCreateNewPatientPhoneEntity() {
        final var expectedPhone = "string phoneM59hG";
        final var expectedPatient = PatientEntity.create("string namempQyxH", LocalDate.now(), "string genderirE6o");

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
        final var expectedPhone = "string phone8xCXf";
        final var expectedPatient = PatientEntity.create("string nameXvXQQg", LocalDate.now(), "string genderqmqVN");

        final var actualPatientPhoneEntity = PatientPhoneEntity.create("string phoneaRCIG",
            PatientEntity.create("string nameCs0DUE", LocalDate.now(), "string genderbTHCH"));
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
        final var expectedId = "string idhpwOv";
        final var expectedPhone = "string phoneUYKnq";
        final var expectedPatient = PatientEntity.create("string nameHyOwcr", LocalDate.now(), "string genderfrWPx");

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
        final var expectedId = "string idg80Rz";
        final var expectedPhone = "string phoneWNMh6";
        final var expectedPatient = PatientEntity.create("string nameokiRDV", LocalDate.now(), "string genderNOMSB");

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
        final var expectedPhone = "string phoneVBpNI";
        final var expectedPatient = PatientEntity.create("string name8L4RjY", LocalDate.now(), "string genderh2PdI");

        final var actualPatientPhoneEntity = PatientPhoneEntity.create(expectedPhone, expectedPatient);
        actualPatientPhoneEntity.delete();
        Assertions.assertNotNull(actualPatientPhoneEntity.getDeletedAt());
        Assertions.assertEquals(expectedPhone, actualPatientPhoneEntity.getPhone());
        Assertions.assertEquals(expectedPatient, actualPatientPhoneEntity.getPatient());
    }
}
