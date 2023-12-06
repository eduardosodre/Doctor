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
        final var expectedCrm = "string crmVse";
        final var expectedName = "string namepvcwtm";
        final var expectedSpecialty = "string specialtyO5L";

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
        "Gd,string name,string specialty,crm must be between 3 and 20 characters",
        "string crm,cvnqb,string specialty,name must be between 6 and 200 characters",
        "string crm,string name,2X,specialty must be between 3 and 100 characters"
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
        final var expectedCrm = "string crmCzx";
        final var expectedName = "string nameQL1S0e";
        final var expectedSpecialty = "string specialtyn9J";

        final var actualDoctorEntity = DoctorEntity.create("string crmQet", "string name4Owf3n", "string specialtypJ5");
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
        "9S,string name,string specialty,crm must be between 3 and 20 characters",
        "string crm,bUtIh,string specialty,name must be between 6 and 200 characters",
        "string crm,string name,uK,specialty must be between 3 and 100 characters"
    })
    void shouldCreateNewDoctorEntityAndNotUpdate(String crm,
                                                 String name,
                                                 String specialty,
                                                 String messageError) {
        final var actualDoctorEntity = DoctorEntity.create("string crmAnM", "string namerJlXqC", "string specialtyfV4");

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
        final var expectedId = "string idfM5us";
        final var expectedCrm = "string crmOuj";
        final var expectedName = "string nameDgfOkU";
        final var expectedSpecialty = "string specialtyJRY";

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
        final var expectedId = "string idIluG2";
        final var expectedCrm = "string crmh3G";
        final var expectedName = "string nameeShhNR";
        final var expectedSpecialty = "string specialtyp39";

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
        final var expectedCrm = "string crmx1Q";
        final var expectedName = "string nameQPfITM";
        final var expectedSpecialty = "string specialtyR0Y";

        final var actualDoctorEntity = DoctorEntity.create(expectedCrm, expectedName, expectedSpecialty);
        actualDoctorEntity.delete();
        Assertions.assertNotNull(actualDoctorEntity.getDeletedAt());
        Assertions.assertEquals(expectedCrm, actualDoctorEntity.getCrm());
        Assertions.assertEquals(expectedName, actualDoctorEntity.getName());
        Assertions.assertEquals(expectedSpecialty, actualDoctorEntity.getSpecialty());
    }
}
