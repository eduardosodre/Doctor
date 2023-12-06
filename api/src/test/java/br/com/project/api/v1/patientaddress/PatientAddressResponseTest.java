package br.com.project.api.v1.patientaddress;

import br.com.project.domain.patient.PatientEntity;
import br.com.project.domain.patientaddress.PatientAddressEntity;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientAddressResponseTest {

    @Test
    void shouldToEntity() {
        final var expectedStreet = "string streetNDTpY";
        final var expectedNumber = "string numberPAnUz";
        final var expectedNeighborhood = "string neighborhood8LeL0";
        final var expectedCity = "string city1KkMP";
        final var expectedState = "string stateZlXOm";
        final var expectedCountry = "string countrypQCOF";
        final var expectedPatient = PatientEntity.create("string nameUrE7Hv", LocalDate.now(), "string genderqOnHP");

        final var actualPatientAddressEntity = PatientAddressEntity.create(expectedStreet, expectedNumber, expectedNeighborhood, expectedCity,
            expectedState, expectedCountry, expectedPatient);
        final var response = PatientAddressResponse.fromEntity(actualPatientAddressEntity);

        Assertions.assertEquals(actualPatientAddressEntity.getId(), response.id());
        Assertions.assertEquals(expectedStreet, response.street());
        Assertions.assertEquals(expectedNumber, response.number());
        Assertions.assertEquals(expectedNeighborhood, response.neighborhood());
        Assertions.assertEquals(expectedCity, response.city());
        Assertions.assertEquals(expectedState, response.state());
        Assertions.assertEquals(expectedCountry, response.country());
    }

}
