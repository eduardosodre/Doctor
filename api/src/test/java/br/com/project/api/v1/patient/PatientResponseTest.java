package br.com.project.api.v1.patient;

import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientResponseTest {

    @Test
    void shouldToEntity() {
        final var expectedName = "string name7If9Hg";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderbij2E";

        final var actualPatientEntity = PatientEntity.create(expectedName, expectedBirthdate, expectedGender);
        final var response = PatientResponse.fromEntity(actualPatientEntity);

        Assertions.assertEquals(actualPatientEntity.getId(), response.id());
        Assertions.assertEquals(expectedName, response.name());
        Assertions.assertEquals(expectedBirthdate, response.birthdate());
        Assertions.assertEquals(expectedGender, response.gender());
    }

}
