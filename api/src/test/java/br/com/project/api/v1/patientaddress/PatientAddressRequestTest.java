package br.com.project.api.v1.patientaddress;

import br.com.project.api.v1.patient.PatientRequest;
import br.com.project.api.v1.patientemail.PatientEmailRequest;
import br.com.project.api.v1.patientphone.PatientPhoneRequest;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientAddressRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedId = "c3984e0f-6064-41b8-a01e-71ed93121631";
        final var expectedStreet = "string streetWhbiQ";
        final var expectedNumber = "string numberWHHhP";
        final var expectedNeighborhood = "string neighborhoodmT8w7";
        final var expectedCity = "string cityyRS04";
        final var expectedState = "string stateecu0z";
        final var expectedCountry = "string countryo0mbE";
        final var actualPatientAddressRequest = new PatientAddressRequest(expectedId, expectedStreet, expectedNumber, expectedNeighborhood,
            expectedCity, expectedState, expectedCountry);

        final var patient = new PatientRequest("string nameK6P8Mi", LocalDate.now(), "string genderfwJc5", List.<PatientPhoneRequest>of(),
            List.<PatientAddressRequest>of(), List.<PatientEmailRequest>of());
        final var entity = actualPatientAddressRequest.toEntity(patient.toEntity());

        Assertions.assertEquals(expectedStreet, entity.getStreet());
        Assertions.assertEquals(expectedNumber, entity.getNumber());
        Assertions.assertEquals(expectedNeighborhood, entity.getNeighborhood());
        Assertions.assertEquals(expectedCity, entity.getCity());
        Assertions.assertEquals(expectedState, entity.getState());
        Assertions.assertEquals(expectedCountry, entity.getCountry());
    }

    @Test
    void shouldToEntityWithoutId() {
        final String expectedId = null;
        final var expectedStreet = "string streetiQKNZ";
        final var expectedNumber = "string numbervTyJN";
        final var expectedNeighborhood = "string neighborhoodlQfN3";
        final var expectedCity = "string city6e7Qg";
        final var expectedState = "string stateXVmR1";
        final var expectedCountry = "string countryI4Q4p";
        final var actualPatientAddressRequest = new PatientAddressRequest(expectedId, expectedStreet, expectedNumber, expectedNeighborhood,
            expectedCity, expectedState, expectedCountry);

        final var patient = new PatientRequest("string nameUHvBsm", LocalDate.now(), "string genderdmF5Z", List.<PatientPhoneRequest>of(),
            List.<PatientAddressRequest>of(), List.<PatientEmailRequest>of());
        final var entity = actualPatientAddressRequest.toEntity(patient.toEntity());

        Assertions.assertEquals(expectedStreet, entity.getStreet());
        Assertions.assertEquals(expectedNumber, entity.getNumber());
        Assertions.assertEquals(expectedNeighborhood, entity.getNeighborhood());
        Assertions.assertEquals(expectedCity, entity.getCity());
        Assertions.assertEquals(expectedState, entity.getState());
        Assertions.assertEquals(expectedCountry, entity.getCountry());
    }

}
