package br.com.project.api.v1.patient;

import br.com.project.api.v1.patientaddress.PatientAddressRequest;
import br.com.project.api.v1.patientemail.PatientEmailRequest;
import br.com.project.api.v1.patientphone.PatientPhoneRequest;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedName = "string nameWqxuq2";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderhoyvn";
        final var expectedPatientPhoneRequest = new PatientPhoneRequest("string idcuVqT", "string phoneMhVq1");
        final var expectedPhones = List.of(expectedPatientPhoneRequest);
        final var expectedPatientAddressRequest = new PatientAddressRequest("string idbaZnd", "string streetongBZ", "string numberXgnRt",
            "string neighborhoodBo9MC", "string cityLhpQr", "string stateB5rKZ", "string countryfakWv");
        final var expectedAddresses = List.of(expectedPatientAddressRequest);
        final var expectedPatientEmailRequest = new PatientEmailRequest("string id4O2Gf", "string emailoakq6");
        final var expectedEmails = List.of(expectedPatientEmailRequest);
        final var actualPatientRequest = new PatientRequest(expectedName, expectedBirthdate, expectedGender, expectedPhones, expectedAddresses,
            expectedEmails);

        final var entity = actualPatientRequest.toEntity();

        Assertions.assertEquals(expectedName, entity.getName());
        Assertions.assertEquals(expectedBirthdate, entity.getBirthdate());
        Assertions.assertEquals(expectedGender, entity.getGender());
        Assertions.assertEquals(expectedPatientPhoneRequest.toEntity(entity).getId(), entity.getPhones().get(0).getId());
        Assertions.assertEquals(expectedPatientAddressRequest.toEntity(entity).getId(), entity.getAddresses().get(0).getId());
        Assertions.assertEquals(expectedPatientEmailRequest.toEntity(entity).getId(), entity.getEmails().get(0).getId());
    }

    @Test
    void shouldToEntityWithId() {
        final var expectedId = "0ae80c8c-16a5-437f-bd63-ee30c2de87a0";
        final var expectedName = "string nameows5sm";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderpYN6v";
        final var expectedPatientPhoneRequest = new PatientPhoneRequest("string idiPe02", "string phoneRelYb");
        final var expectedPhones = List.of(expectedPatientPhoneRequest);
        final var expectedPatientAddressRequest = new PatientAddressRequest("string idMLUCu", "string streetSrgCS", "string numberGZY7L",
            "string neighborhoodjQruV", "string cityG8V6o", "string stateVQ0ub", "string countryFK33D");
        final var expectedAddresses = List.of(expectedPatientAddressRequest);
        final var expectedPatientEmailRequest = new PatientEmailRequest("string idK80zd", "string emailYmE1w");
        final var expectedEmails = List.of(expectedPatientEmailRequest);
        final var actualPatientRequest = new PatientRequest(expectedName, expectedBirthdate, expectedGender, expectedPhones, expectedAddresses,
            expectedEmails);

        final var entity = actualPatientRequest.toEntity(expectedId);

        Assertions.assertEquals(expectedId, entity.getId());
        Assertions.assertEquals(expectedName, entity.getName());
        Assertions.assertEquals(expectedBirthdate, entity.getBirthdate());
        Assertions.assertEquals(expectedGender, entity.getGender());
        Assertions.assertEquals(expectedPatientPhoneRequest.toEntity(entity).getId(), entity.getPhones().get(0).getId());
        Assertions.assertEquals(expectedPatientAddressRequest.toEntity(entity).getId(), entity.getAddresses().get(0).getId());
        Assertions.assertEquals(expectedPatientEmailRequest.toEntity(entity).getId(), entity.getEmails().get(0).getId());
    }
}
