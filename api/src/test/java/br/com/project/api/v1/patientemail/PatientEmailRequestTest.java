package br.com.project.api.v1.patientemail;

import br.com.project.api.v1.patient.PatientRequest;
import br.com.project.api.v1.patientaddress.PatientAddressRequest;
import br.com.project.api.v1.patientphone.PatientPhoneRequest;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientEmailRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedId = "1f5b3706-b32c-4940-b666-696ad7c98907";
        final var expectedEmail = "string emailDRaTu";
        final var actualPatientEmailRequest = new PatientEmailRequest(expectedId, expectedEmail);

        final var patient = new PatientRequest("string namekEltSZ", LocalDate.now(), "string genderIIZEY", List.<PatientPhoneRequest>of(),
            List.<PatientAddressRequest>of(), List.<PatientEmailRequest>of());
        final var entity = actualPatientEmailRequest.toEntity(patient.toEntity());

        Assertions.assertEquals(expectedEmail, entity.getEmail());
    }

    @Test
    void shouldToEntityWithoutId() {
        final String expectedId = null;
        final var expectedEmail = "string emailfMKS3";
        final var actualPatientEmailRequest = new PatientEmailRequest(expectedId, expectedEmail);

        final var patient = new PatientRequest("string namet22VQR", LocalDate.now(), "string genderrH3g2", List.<PatientPhoneRequest>of(),
            List.<PatientAddressRequest>of(), List.<PatientEmailRequest>of());
        final var entity = actualPatientEmailRequest.toEntity(patient.toEntity());

        Assertions.assertEquals(expectedEmail, entity.getEmail());
    }

}
