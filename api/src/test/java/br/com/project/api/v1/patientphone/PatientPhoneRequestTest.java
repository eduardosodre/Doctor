package br.com.project.api.v1.patientphone;

import br.com.project.api.v1.patient.PatientRequest;
import br.com.project.api.v1.patientaddress.PatientAddressRequest;
import br.com.project.api.v1.patientemail.PatientEmailRequest;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientPhoneRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedId = "43143ae3-5182-43f1-a2bb-a6ab702ad139";
        final var expectedPhone = "string phoneCtSKc";
        final var actualPatientPhoneRequest = new PatientPhoneRequest(expectedId, expectedPhone);

        final var patient = new PatientRequest("string nameH9dwDt", LocalDate.now(), "string genderW8Y75", List.<PatientPhoneRequest>of(),
            List.<PatientAddressRequest>of(), List.<PatientEmailRequest>of());
        final var entity = actualPatientPhoneRequest.toEntity(patient.toEntity());

        Assertions.assertEquals(expectedPhone, entity.getPhone());
    }

    @Test
    void shouldToEntityWithoutId() {
        final String expectedId = null;
        final var expectedPhone = "string phone9Jg6p";
        final var actualPatientPhoneRequest = new PatientPhoneRequest(expectedId, expectedPhone);

        final var patient = new PatientRequest("string nameeVXgTk", LocalDate.now(), "string genderefuVM", List.<PatientPhoneRequest>of(),
            List.<PatientAddressRequest>of(), List.<PatientEmailRequest>of());
        final var entity = actualPatientPhoneRequest.toEntity(patient.toEntity());

        Assertions.assertEquals(expectedPhone, entity.getPhone());
    }

}
