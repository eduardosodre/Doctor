package br.com.project.domain.patient;

import br.com.project.domain.exceptions.NotificationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PatientEntityTest {

    @Test
    void shouldCreateNewPatientEntity() {
        final var expectedName = "string name6NEOMA";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderZHwnu";

        final var actualPatientEntity = PatientEntity.create(expectedName, expectedBirthdate, expectedGender);
        Assertions.assertNotNull(actualPatientEntity);
        Assertions.assertNotNull(actualPatientEntity.getId());
        Assertions.assertNotNull(actualPatientEntity.getCreatedAt());
        Assertions.assertNull(actualPatientEntity.getUpdatedAt());
        Assertions.assertNull(actualPatientEntity.getDeletedAt());
        Assertions.assertEquals(expectedName, actualPatientEntity.getName());
        Assertions.assertEquals(expectedBirthdate, actualPatientEntity.getBirthdate());
        Assertions.assertEquals(expectedGender, actualPatientEntity.getGender());
    }

    @ParameterizedTest
    @CsvSource({
        "DL1xt,name must be between 6 and 200 characters"
    })
    void shouldNotCreateNewPatientEntity(String name,
                                         String messageError) {
        final var birthdate = LocalDate.now();
        final var gender = "string genderV4PiG";

        final var expectedError = Assertions.assertThrows(NotificationException.class,
            () -> PatientEntity.create(name,
                birthdate,
                gender));

        Assertions.assertEquals(messageError, expectedError.getErrors().get(0).message());
    }

    @Test
    void shouldCreateNewPatientEntityAndUpdate() {
        final var expectedName = "string nameiTzyEz";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderO46J8";

        final var actualPatientEntity = PatientEntity.create("string nameu6z3Uz", LocalDate.now(), "string genderh7bXi");
        final var id = actualPatientEntity.getId();

        actualPatientEntity.setPhones(null);
        actualPatientEntity.setPhones(List.of());

        actualPatientEntity.setAddresses(null);
        actualPatientEntity.setAddresses(List.of());

        actualPatientEntity.setEmails(null);
        actualPatientEntity.setEmails(List.of());

        actualPatientEntity.update(expectedName, expectedBirthdate, expectedGender);

        Assertions.assertNotNull(actualPatientEntity);
        Assertions.assertEquals(id, actualPatientEntity.getId());
        Assertions.assertNotNull(actualPatientEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientEntity.getUpdatedAt());
        Assertions.assertNull(actualPatientEntity.getDeletedAt());
        Assertions.assertEquals(expectedName, actualPatientEntity.getName());
        Assertions.assertEquals(expectedBirthdate, actualPatientEntity.getBirthdate());
        Assertions.assertEquals(expectedGender, actualPatientEntity.getGender());
    }

    @ParameterizedTest
    @CsvSource({
        "l2J7g,name must be between 6 and 200 characters"
    })
    void shouldCreateNewPatientEntityAndNotUpdate(String name,
                                                  String messageError) {
        final var actualPatientEntity = PatientEntity.create("string nameTsCXGC", LocalDate.now(), "string genderbL8MY");
        final var birthdate = LocalDate.now();
        final var gender = "string genderFdHC9";

        final var expectedError = Assertions.assertThrows(NotificationException.class,
            () -> actualPatientEntity.update(name,
                birthdate,
                gender));

        Assertions.assertEquals(messageError, expectedError.getErrors().get(0).message());
    }

    @Test
    void shouldCopyWithPatientEntity() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idUpg0e";
        final var expectedName = "string namev1FS8o";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderEJ5bG";

        final var actualPatientEntity = PatientEntity.with(expectedId, expectedName, expectedBirthdate, expectedGender, expectedCreated,
            expectedUpdated, expectedDeleted);
        Assertions.assertNotNull(actualPatientEntity);
        Assertions.assertNotNull(actualPatientEntity.getId());
        Assertions.assertNotNull(actualPatientEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientEntity.getUpdatedAt());
        Assertions.assertNotNull(actualPatientEntity.getDeletedAt());
        Assertions.assertEquals(expectedName, actualPatientEntity.getName());
        Assertions.assertEquals(expectedBirthdate, actualPatientEntity.getBirthdate());
        Assertions.assertEquals(expectedGender, actualPatientEntity.getGender());
    }

    @Test
    void shouldCopyWithPatientEntityFromObject() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idpATeo";
        final var expectedName = "string nameWf79ey";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderhzgYz";

        final var actualPatientEntity = PatientEntity.with(expectedId, expectedName, expectedBirthdate, expectedGender, expectedCreated,
            expectedUpdated, expectedDeleted);
        final var copyFromPatientEntity = PatientEntity.with(actualPatientEntity);
        Assertions.assertNotNull(actualPatientEntity);
        Assertions.assertNotNull(actualPatientEntity.getId());
        Assertions.assertNotNull(actualPatientEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientEntity.getUpdatedAt());
        Assertions.assertNotNull(actualPatientEntity.getDeletedAt());
        Assertions.assertEquals(expectedName, actualPatientEntity.getName());
        Assertions.assertEquals(expectedBirthdate, actualPatientEntity.getBirthdate());
        Assertions.assertEquals(expectedGender, actualPatientEntity.getGender());
        Assertions.assertEquals(actualPatientEntity.getName(), copyFromPatientEntity.getName());
        Assertions.assertEquals(actualPatientEntity.getBirthdate(), copyFromPatientEntity.getBirthdate());
        Assertions.assertEquals(actualPatientEntity.getGender(), copyFromPatientEntity.getGender());
        Assertions.assertEquals(actualPatientEntity.getCreatedAt(), copyFromPatientEntity.getCreatedAt());
        Assertions.assertEquals(actualPatientEntity.getUpdatedAt(), copyFromPatientEntity.getUpdatedAt());
        Assertions.assertEquals(actualPatientEntity.getDeletedAt(), copyFromPatientEntity.getDeletedAt());
    }

    @Test
    void shouldCreateNewPatientEntityAndDelete() {
        final var expectedName = "string nameCw4EV7";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string gendereWg1d";

        final var actualPatientEntity = PatientEntity.create(expectedName, expectedBirthdate, expectedGender);
        actualPatientEntity.delete();
        Assertions.assertNotNull(actualPatientEntity.getDeletedAt());
        Assertions.assertEquals(expectedName, actualPatientEntity.getName());
        Assertions.assertEquals(expectedBirthdate, actualPatientEntity.getBirthdate());
        Assertions.assertEquals(expectedGender, actualPatientEntity.getGender());
    }
}
