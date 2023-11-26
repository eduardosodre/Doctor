package br.com.project.domain.patientaddress;

import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientAddressEntityTest {
 
    @Test
    void shouldCreateNewPatientAddressEntity() {
        final var expectedStreet = "string streetES5Ve";
        final var expectedNumber = "string numberFygee";
        final var expectedNeighborhood = "string neighborhoodL6yQp";
        final var expectedCity = "string citylKIJf";
        final var expectedState = "string state1Ur5D";
        final var expectedCountry = "string countryhEoIS";
        final var expectedPatient = PatientEntity.create("string namehcz7Ay", LocalDate.now(), "string gendero6M10");

        final var actualPatientAddressEntity = PatientAddressEntity.create(expectedStreet, expectedNumber, expectedNeighborhood, expectedCity,
            expectedState, expectedCountry, expectedPatient);
        Assertions.assertNotNull(actualPatientAddressEntity);
        Assertions.assertNotNull(actualPatientAddressEntity.getId());
        Assertions.assertNotNull(actualPatientAddressEntity.getCreatedAt());
        Assertions.assertNull(actualPatientAddressEntity.getUpdatedAt());
        Assertions.assertNull(actualPatientAddressEntity.getDeletedAt());
        Assertions.assertEquals(expectedStreet, actualPatientAddressEntity.getStreet());
        Assertions.assertEquals(expectedNumber, actualPatientAddressEntity.getNumber());
        Assertions.assertEquals(expectedNeighborhood, actualPatientAddressEntity.getNeighborhood());
        Assertions.assertEquals(expectedCity, actualPatientAddressEntity.getCity());
        Assertions.assertEquals(expectedState, actualPatientAddressEntity.getState());
        Assertions.assertEquals(expectedCountry, actualPatientAddressEntity.getCountry());
        Assertions.assertEquals(expectedPatient, actualPatientAddressEntity.getPatient());
    }

    @Test
    void shouldCreateNewPatientAddressEntityAndUpdate() {
        final var expectedStreet = "string streetw5VR0";
        final var expectedNumber = "string numberJRnp4";
        final var expectedNeighborhood = "string neighborhoodO4QWw";
        final var expectedCity = "string cityqDMHX";
        final var expectedState = "string state7Swhp";
        final var expectedCountry = "string countryNFzL2";
        final var expectedPatient = PatientEntity.create("string nameqWKVWq", LocalDate.now(), "string genderTxlix");

        final var actualPatientAddressEntity = PatientAddressEntity.create("string streetiEtUC", "string numberdASkJ", "string neighborhoodEc5Xx",
            "string city1HW2n", "string stateMbheU", "string countrySqKUx",
            PatientEntity.create("string nameF7CAeZ", LocalDate.now(), "string genderXbSmh"));
        final var id = actualPatientAddressEntity.getId();

        actualPatientAddressEntity.update(expectedStreet, expectedNumber, expectedNeighborhood, expectedCity, expectedState, expectedCountry);

        Assertions.assertNotNull(actualPatientAddressEntity);
        Assertions.assertEquals(id, actualPatientAddressEntity.getId());
        Assertions.assertNotNull(actualPatientAddressEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientAddressEntity.getUpdatedAt());
        Assertions.assertNull(actualPatientAddressEntity.getDeletedAt());
        Assertions.assertEquals(expectedStreet, actualPatientAddressEntity.getStreet());
        Assertions.assertEquals(expectedNumber, actualPatientAddressEntity.getNumber());
        Assertions.assertEquals(expectedNeighborhood, actualPatientAddressEntity.getNeighborhood());
        Assertions.assertEquals(expectedCity, actualPatientAddressEntity.getCity());
        Assertions.assertEquals(expectedState, actualPatientAddressEntity.getState());
        Assertions.assertEquals(expectedCountry, actualPatientAddressEntity.getCountry());
    }

    @Test
    void shouldCopyWithPatientAddressEntity() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idxTc0V";
        final var expectedStreet = "string street7ojGY";
        final var expectedNumber = "string numberWgwqv";
        final var expectedNeighborhood = "string neighborhoodgqVjO";
        final var expectedCity = "string citycFH78";
        final var expectedState = "string stateXaayh";
        final var expectedCountry = "string countryVtQ4H";
        final var expectedPatient = PatientEntity.create("string nameqLXhrg", LocalDate.now(), "string genderUp7WL");

        final var actualPatientAddressEntity = PatientAddressEntity.with(expectedId, expectedStreet, expectedNumber, expectedNeighborhood,
            expectedCity, expectedState, expectedCountry, expectedPatient, expectedCreated, expectedUpdated, expectedDeleted);
        Assertions.assertNotNull(actualPatientAddressEntity);
        Assertions.assertNotNull(actualPatientAddressEntity.getId());
        Assertions.assertNotNull(actualPatientAddressEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientAddressEntity.getUpdatedAt());
        Assertions.assertNotNull(actualPatientAddressEntity.getDeletedAt());
        Assertions.assertEquals(expectedStreet, actualPatientAddressEntity.getStreet());
        Assertions.assertEquals(expectedNumber, actualPatientAddressEntity.getNumber());
        Assertions.assertEquals(expectedNeighborhood, actualPatientAddressEntity.getNeighborhood());
        Assertions.assertEquals(expectedCity, actualPatientAddressEntity.getCity());
        Assertions.assertEquals(expectedState, actualPatientAddressEntity.getState());
        Assertions.assertEquals(expectedCountry, actualPatientAddressEntity.getCountry());
        Assertions.assertEquals(expectedPatient, actualPatientAddressEntity.getPatient());
    }

    @Test
    void shouldCopyWithPatientAddressEntityFromObject() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idcOJwB";
        final var expectedStreet = "string street53OXx";
        final var expectedNumber = "string numberqpF0r";
        final var expectedNeighborhood = "string neighborhoodu6AzS";
        final var expectedCity = "string city8JF1D";
        final var expectedState = "string statehDS2y";
        final var expectedCountry = "string country8DdWC";
        final var expectedPatient = PatientEntity.create("string namewfb2QK", LocalDate.now(), "string genderKNoDj");

        final var actualPatientAddressEntity = PatientAddressEntity.with(expectedId, expectedStreet, expectedNumber, expectedNeighborhood,
            expectedCity, expectedState, expectedCountry, expectedPatient, expectedCreated, expectedUpdated, expectedDeleted);
        final var copyFromPatientAddressEntity = PatientAddressEntity.with(actualPatientAddressEntity);
        Assertions.assertNotNull(actualPatientAddressEntity);
        Assertions.assertNotNull(actualPatientAddressEntity.getId());
        Assertions.assertNotNull(actualPatientAddressEntity.getCreatedAt());
        Assertions.assertNotNull(actualPatientAddressEntity.getUpdatedAt());
        Assertions.assertNotNull(actualPatientAddressEntity.getDeletedAt());
        Assertions.assertEquals(expectedStreet, actualPatientAddressEntity.getStreet());
        Assertions.assertEquals(expectedNumber, actualPatientAddressEntity.getNumber());
        Assertions.assertEquals(expectedNeighborhood, actualPatientAddressEntity.getNeighborhood());
        Assertions.assertEquals(expectedCity, actualPatientAddressEntity.getCity());
        Assertions.assertEquals(expectedState, actualPatientAddressEntity.getState());
        Assertions.assertEquals(expectedCountry, actualPatientAddressEntity.getCountry());
        Assertions.assertEquals(expectedPatient, actualPatientAddressEntity.getPatient());
        Assertions.assertEquals(actualPatientAddressEntity.getStreet(), copyFromPatientAddressEntity.getStreet());
        Assertions.assertEquals(actualPatientAddressEntity.getNumber(), copyFromPatientAddressEntity.getNumber());
        Assertions.assertEquals(actualPatientAddressEntity.getNeighborhood(), copyFromPatientAddressEntity.getNeighborhood());
        Assertions.assertEquals(actualPatientAddressEntity.getCity(), copyFromPatientAddressEntity.getCity());
        Assertions.assertEquals(actualPatientAddressEntity.getState(), copyFromPatientAddressEntity.getState());
        Assertions.assertEquals(actualPatientAddressEntity.getCountry(), copyFromPatientAddressEntity.getCountry());
        Assertions.assertEquals(actualPatientAddressEntity.getPatient(), copyFromPatientAddressEntity.getPatient());
        Assertions.assertEquals(actualPatientAddressEntity.getCreatedAt(), copyFromPatientAddressEntity.getCreatedAt());
        Assertions.assertEquals(actualPatientAddressEntity.getUpdatedAt(), copyFromPatientAddressEntity.getUpdatedAt());
        Assertions.assertEquals(actualPatientAddressEntity.getDeletedAt(), copyFromPatientAddressEntity.getDeletedAt());
    }

    @Test
    void shouldCreateNewPatientAddressEntityAndDelete() {
        final var expectedStreet = "string streetBhSNk";
        final var expectedNumber = "string numberM1Rvq";
        final var expectedNeighborhood = "string neighborhoodF84Xs";
        final var expectedCity = "string cityujnhj";
        final var expectedState = "string state5WKzw";
        final var expectedCountry = "string country88ZRe";
        final var expectedPatient = PatientEntity.create("string name1mJxqx", LocalDate.now(), "string gender8ppfm");

        final var actualPatientAddressEntity = PatientAddressEntity.create(expectedStreet, expectedNumber, expectedNeighborhood, expectedCity,
            expectedState, expectedCountry, expectedPatient);
        actualPatientAddressEntity.delete();
        Assertions.assertNotNull(actualPatientAddressEntity.getDeletedAt());
        Assertions.assertEquals(expectedStreet, actualPatientAddressEntity.getStreet());
        Assertions.assertEquals(expectedNumber, actualPatientAddressEntity.getNumber());
        Assertions.assertEquals(expectedNeighborhood, actualPatientAddressEntity.getNeighborhood());
        Assertions.assertEquals(expectedCity, actualPatientAddressEntity.getCity());
        Assertions.assertEquals(expectedState, actualPatientAddressEntity.getState());
        Assertions.assertEquals(expectedCountry, actualPatientAddressEntity.getCountry());
        Assertions.assertEquals(expectedPatient, actualPatientAddressEntity.getPatient());
    }
}
