package br.com.project.domain.patientemail;

import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientEmailEntityTest {

    @Test
    void shouldCreateNewPatientEmailEntity() {
        final var expectedEmail = "string emailIHhkx";
        final var expectedPatient = PatientEntity.create("string nameNdNYda", LocalDate.now(), "string gender2ye8w");

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
        final var expectedEmail = "string emailrDuKh";
        final var expectedPatient = PatientEntity.create("string nameOZaTKu", LocalDate.now(), "string gendervnwNE");

        final var actualPatientEmailEntity = PatientEmailEntity.create("string email91efZ",
            PatientEntity.create("string nameTSODTR", LocalDate.now(), "string genderXrdPa"));
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
        final var expectedId = "string idTjEe7";
        final var expectedEmail = "string email2WxJN";
        final var expectedPatient = PatientEntity.create("string namemVL4tl", LocalDate.now(), "string genderCY2Pu");

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
        final var expectedId = "string idj719o";
        final var expectedEmail = "string emailhGisk";
        final var expectedPatient = PatientEntity.create("string named1uz3f", LocalDate.now(), "string genderQB3Ov");

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
        final var expectedEmail = "string emailuiiiJ";
        final var expectedPatient = PatientEntity.create("string nameebsIyV", LocalDate.now(), "string genderycKwN");

        final var actualPatientEmailEntity = PatientEmailEntity.create(expectedEmail, expectedPatient);
        actualPatientEmailEntity.delete();
        Assertions.assertNotNull(actualPatientEmailEntity.getDeletedAt());
        Assertions.assertEquals(expectedEmail, actualPatientEmailEntity.getEmail());
        Assertions.assertEquals(expectedPatient, actualPatientEmailEntity.getPatient());
    }
}
