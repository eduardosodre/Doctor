package br.com.project.domain.doctor;

import br.com.project.domain.exceptions.NotificationException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DoctorEntityTest {
 
    @Test
    void shouldCreateNewDoctorEntity() {
        final var expectedCrm = "string crmit3";
        final var expectedName = "string nameIoSdZl";
        final var expectedSpecialty = "string specialtyIHO";

        final var actualDoctorEntity = DoctorEntity.create(expectedCrm, expectedName, expectedSpecialty);
        Assertions.assertNotNull(actualDoctorEntity);
        Assertions.assertNotNull(actualDoctorEntity.getId());
        Assertions.assertNotNull(actualDoctorEntity.getCreatedAt());
        Assertions.assertNull(actualDoctorEntity.getUpdatedAt());
        Assertions.assertNull(actualDoctorEntity.getDeletedAt());
        Assertions.assertEquals(expectedCrm, actualDoctorEntity.getCrm());
        Assertions.assertEquals(expectedName, actualDoctorEntity.getName());
        Assertions.assertEquals(expectedSpecialty, actualDoctorEntity.getSpecialty());
    }

    @ParameterizedTest
    @CsvSource({
        "ik,string name,string specialty,crm must be between 3 and 20 characters",
        "string crm,bsPpm,string specialty,name must be between 6 and 200 characters",
        "string crm,string name,kr,specialty must be between 3 and 100 characters"
    })
    void shouldNotCreateNewDoctorEntity(String crm,
                                        String name,
                                        String specialty,
                                        String messageError) {

        final var expectedError = Assertions.assertThrows(NotificationException.class,
            () -> DoctorEntity.create(crm,
                name,
                specialty));

        Assertions.assertEquals(messageError, expectedError.getErrors().get(0).message());
    }

    @Test
    void shouldCreateNewDoctorEntityAndUpdate() {
        final var expectedCrm = "string crml9u";
        final var expectedName = "string name7RbnPD";
        final var expectedSpecialty = "string specialtyqvb";

        final var actualDoctorEntity = DoctorEntity.create("string crm9Ba", "string nameHynGCi", "string specialtypkU");
        final var id = actualDoctorEntity.getId();

        actualDoctorEntity.update(expectedCrm, expectedName, expectedSpecialty);

        Assertions.assertNotNull(actualDoctorEntity);
        Assertions.assertEquals(id, actualDoctorEntity.getId());
        Assertions.assertNotNull(actualDoctorEntity.getCreatedAt());
        Assertions.assertNotNull(actualDoctorEntity.getUpdatedAt());
        Assertions.assertNull(actualDoctorEntity.getDeletedAt());
        Assertions.assertEquals(expectedCrm, actualDoctorEntity.getCrm());
        Assertions.assertEquals(expectedName, actualDoctorEntity.getName());
        Assertions.assertEquals(expectedSpecialty, actualDoctorEntity.getSpecialty());
    }

    @ParameterizedTest
    @CsvSource({
        "hf,string name,string specialty,crm must be between 3 and 20 characters",
        "string crm,9Mb1E,string specialty,name must be between 6 and 200 characters",
        "string crm,string name,MF,specialty must be between 3 and 100 characters"
    })
    void shouldCreateNewDoctorEntityAndNotUpdate(String crm,
                                                 String name,
                                                 String specialty,
                                                 String messageError) {
        final var actualDoctorEntity = DoctorEntity.create("string crmdpW", "string nameAvkW9X", "string specialtyHMN");

        final var expectedError = Assertions.assertThrows(NotificationException.class,
            () -> actualDoctorEntity.update(crm,
                name,
                specialty));

        Assertions.assertEquals(messageError, expectedError.getErrors().get(0).message());
    }

    @Test
    void shouldCopyWithDoctorEntity() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idHj7T7";
        final var expectedCrm = "string crm4gQ";
        final var expectedName = "string nameaxRasS";
        final var expectedSpecialty = "string specialtylDj";

        final var actualDoctorEntity = DoctorEntity.with(expectedId, expectedCrm, expectedName, expectedSpecialty, expectedCreated, expectedUpdated,
            expectedDeleted);
        Assertions.assertNotNull(actualDoctorEntity);
        Assertions.assertNotNull(actualDoctorEntity.getId());
        Assertions.assertNotNull(actualDoctorEntity.getCreatedAt());
        Assertions.assertNotNull(actualDoctorEntity.getUpdatedAt());
        Assertions.assertNotNull(actualDoctorEntity.getDeletedAt());
        Assertions.assertEquals(expectedCrm, actualDoctorEntity.getCrm());
        Assertions.assertEquals(expectedName, actualDoctorEntity.getName());
        Assertions.assertEquals(expectedSpecialty, actualDoctorEntity.getSpecialty());
    }

    @Test
    void shouldCopyWithDoctorEntityFromObject() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string id2KeUj";
        final var expectedCrm = "string crmfOQ";
        final var expectedName = "string nameR6cvJi";
        final var expectedSpecialty = "string specialtyxJ1";

        final var actualDoctorEntity = DoctorEntity.with(expectedId, expectedCrm, expectedName, expectedSpecialty, expectedCreated, expectedUpdated,
            expectedDeleted);
        final var copyFromDoctorEntity = DoctorEntity.with(actualDoctorEntity);
        Assertions.assertNotNull(actualDoctorEntity);
        Assertions.assertNotNull(actualDoctorEntity.getId());
        Assertions.assertNotNull(actualDoctorEntity.getCreatedAt());
        Assertions.assertNotNull(actualDoctorEntity.getUpdatedAt());
        Assertions.assertNotNull(actualDoctorEntity.getDeletedAt());
        Assertions.assertEquals(expectedCrm, actualDoctorEntity.getCrm());
        Assertions.assertEquals(expectedName, actualDoctorEntity.getName());
        Assertions.assertEquals(expectedSpecialty, actualDoctorEntity.getSpecialty());
        Assertions.assertEquals(actualDoctorEntity.getCrm(), copyFromDoctorEntity.getCrm());
        Assertions.assertEquals(actualDoctorEntity.getName(), copyFromDoctorEntity.getName());
        Assertions.assertEquals(actualDoctorEntity.getSpecialty(), copyFromDoctorEntity.getSpecialty());
        Assertions.assertEquals(actualDoctorEntity.getCreatedAt(), copyFromDoctorEntity.getCreatedAt());
        Assertions.assertEquals(actualDoctorEntity.getUpdatedAt(), copyFromDoctorEntity.getUpdatedAt());
        Assertions.assertEquals(actualDoctorEntity.getDeletedAt(), copyFromDoctorEntity.getDeletedAt());
    }

    @Test
    void shouldCreateNewDoctorEntityAndDelete() {
        final var expectedCrm = "string crmc7s";
        final var expectedName = "string nameGLVTtU";
        final var expectedSpecialty = "string specialtyccz";

        final var actualDoctorEntity = DoctorEntity.create(expectedCrm, expectedName, expectedSpecialty);
        actualDoctorEntity.delete();
        Assertions.assertNotNull(actualDoctorEntity.getDeletedAt());
        Assertions.assertEquals(expectedCrm, actualDoctorEntity.getCrm());
        Assertions.assertEquals(expectedName, actualDoctorEntity.getName());
        Assertions.assertEquals(expectedSpecialty, actualDoctorEntity.getSpecialty());
    }
}
