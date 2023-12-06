package br.com.project.api.v1.patientphone;

import br.com.project.domain.patient.PatientEntity;
import br.com.project.domain.patientphone.PatientPhoneEntity;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientPhoneResponseTest {

    @Test
    void shouldToEntity() {
        final var expectedPhone = "string phone0U5CJ";
        final var expectedPatient = PatientEntity.create("string nameyExlg3", LocalDate.now(), "string gendervliay");

        final var actualPatientPhoneEntity = PatientPhoneEntity.create(expectedPhone, expectedPatient);
        final var response = PatientPhoneResponse.fromEntity(actualPatientPhoneEntity);

        Assertions.assertEquals(actualPatientPhoneEntity.getId(), response.id());
        Assertions.assertEquals(expectedPhone, response.phone());
    }

}
