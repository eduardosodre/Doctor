package br.com.project.api.v1.patient;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientFilterRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedId = "string idWc2M4";
        final var expectedName = "string namemGy6Fz";
        final var expectedInitialBirthdate = LocalDate.now();
        final var expectedFinalBirthdate = LocalDate.now();
        final var expectedGender = "string gender27YdU";
        final var actualPatientFilterRequest = new PatientFilterRequest(expectedId, expectedName, expectedInitialBirthdate, expectedFinalBirthdate,
            expectedGender);

        final var entity = actualPatientFilterRequest.toEntity();

        Assertions.assertEquals(expectedId, entity.getId());
        Assertions.assertEquals(expectedName, entity.getName());
        Assertions.assertEquals(expectedInitialBirthdate, entity.getInitialBirthdate());
        Assertions.assertEquals(expectedFinalBirthdate, entity.getFinalBirthdate());
        Assertions.assertEquals(expectedGender, entity.getGender());
    }

}
