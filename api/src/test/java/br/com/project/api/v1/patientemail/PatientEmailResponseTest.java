package br.com.project.api.v1.patientemail;

import br.com.project.domain.patient.PatientEntity;
import br.com.project.domain.patientemail.PatientEmailEntity;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientEmailResponseTest {

    @Test
    void shouldToEntity() {
        final var expectedEmail = "string emailqRmxw";
        final var expectedPatient = PatientEntity.create("string nameB6Luta", LocalDate.now(), "string gender3z0kI");

        final var actualPatientEmailEntity = PatientEmailEntity.create(expectedEmail, expectedPatient);
        final var response = PatientEmailResponse.fromEntity(actualPatientEmailEntity);

        Assertions.assertEquals(actualPatientEmailEntity.getId(), response.id());
        Assertions.assertEquals(expectedEmail, response.email());
    }

}
