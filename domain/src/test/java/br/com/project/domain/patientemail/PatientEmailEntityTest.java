package br.com.project.domain.patientemail;

import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientEmailEntityTest {
 
    @Test
    void shouldCreateNewPatientEmailEntity() {
        final var expectedEmail = "string emailyH0cp";
        final var expectedPatient = PatientEntity.create("string name4phR64", LocalDate.now(), "string genderOu5x8");

        final var actualPatientEmailEntity = PatientEmailEntity.create(expectedEmail, expectedPatient);
        Assertions.assertNotNull(actualPatientEmailEntity);
        Assertions.assertNotNull(actualPatientEmailEntity.getId());
        Assertions.assertNotNull(actualPatientEmailEntity.getCreatedAt());
        Assertions.assertNull(actualPatientEmailEntity.getUpdatedAt());
        Assertions.assertNull(actualPatientEmailEntity.getDeletedAt());
        Assertions.assertEquals(expectedEmail, actualPatientEmailEntity.getEmail());
        Assertions.assertEquals(expectedPatient, actualPatientEmailEntity.getPatient());
    }

    @Test
    void shouldCreateNewPatientEmailEntityAndUpdate() {
        final var expectedEmail = "string emailE2i26";
        final var expectedPatient = PatientEntity.create("string nameHMtMCA", LocalDate.now(), "string gender1IhW8");

        final var actualPatientEmailEntity = PatientEmailEntity.create("string email2v0KO",
            PatientEntity.create("string name8T8wzg", LocalDate.now(), "string genderpGCBs"));
        final var id = actualPatientEmailEntity.getId();

        actualPatientEmailEntity.update(expectedEmail);

        Assertions.assertNotNull(actualPatientEmailEntity);
        Assertions.assertEquals(id, actualPatientEmailEntity.getId());
        Assertions.assertNotNull(actualPatientEmailEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientEmailEntity.getUpdatedAt());
        Assertions.assertNull(actualPatientEmailEntity.getDeletedAt());
        Assertions.assertEquals(expectedEmail, actualPatientEmailEntity.getEmail());
    }

    @Test
    void shouldCopyWithPatientEmailEntity() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idEHRNs";
        final var expectedEmail = "string emailBYLhd";
        final var expectedPatient = PatientEntity.create("string nameps69xl", LocalDate.now(), "string genderW6WwN");

        final var actualPatientEmailEntity = PatientEmailEntity.with(expectedId, expectedEmail, expectedPatient, expectedCreated, expectedUpdated,
            expectedDeleted);
        Assertions.assertNotNull(actualPatientEmailEntity);
        Assertions.assertNotNull(actualPatientEmailEntity.getId());
        Assertions.assertNotNull(actualPatientEmailEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientEmailEntity.getUpdatedAt());
        Assertions.assertNotNull(actualPatientEmailEntity.getDeletedAt());
        Assertions.assertEquals(expectedEmail, actualPatientEmailEntity.getEmail());
        Assertions.assertEquals(expectedPatient, actualPatientEmailEntity.getPatient());
    }

    @Test
    void shouldCopyWithPatientEmailEntityFromObject() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idnP0eD";
        final var expectedEmail = "string emailsfvi9";
        final var expectedPatient = PatientEntity.create("string name9RdxkQ", LocalDate.now(), "string genderUB7mQ");

        final var actualPatientEmailEntity = PatientEmailEntity.with(expectedId, expectedEmail, expectedPatient, expectedCreated, expectedUpdated,
            expectedDeleted);
        final var copyFromPatientEmailEntity = PatientEmailEntity.with(actualPatientEmailEntity);
        Assertions.assertNotNull(actualPatientEmailEntity);
        Assertions.assertNotNull(actualPatientEmailEntity.getId());
        Assertions.assertNotNull(actualPatientEmailEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientEmailEntity.getUpdatedAt());
        Assertions.assertNotNull(actualPatientEmailEntity.getDeletedAt());
        Assertions.assertEquals(expectedEmail, actualPatientEmailEntity.getEmail());
        Assertions.assertEquals(expectedPatient, actualPatientEmailEntity.getPatient());
        Assertions.assertEquals(actualPatientEmailEntity.getEmail(), copyFromPatientEmailEntity.getEmail());
        Assertions.assertEquals(actualPatientEmailEntity.getPatient(), copyFromPatientEmailEntity.getPatient());
        Assertions.assertEquals(actualPatientEmailEntity.getCreatedAt(), copyFromPatientEmailEntity.getCreatedAt());
        Assertions.assertEquals(actualPatientEmailEntity.getUpdatedAt(), copyFromPatientEmailEntity.getUpdatedAt());
        Assertions.assertEquals(actualPatientEmailEntity.getDeletedAt(), copyFromPatientEmailEntity.getDeletedAt());
    }

    @Test
    void shouldCreateNewPatientEmailEntityAndDelete() {
        final var expectedEmail = "string emailfQYBN";
        final var expectedPatient = PatientEntity.create("string namesWC8zr", LocalDate.now(), "string genderK4zsu");

        final var actualPatientEmailEntity = PatientEmailEntity.create(expectedEmail, expectedPatient);
        actualPatientEmailEntity.delete();
        Assertions.assertNotNull(actualPatientEmailEntity.getDeletedAt());
        Assertions.assertEquals(expectedEmail, actualPatientEmailEntity.getEmail());
        Assertions.assertEquals(expectedPatient, actualPatientEmailEntity.getPatient());
    }
}
