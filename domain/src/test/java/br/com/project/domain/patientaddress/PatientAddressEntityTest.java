package br.com.project.domain.patientaddress;

import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientAddressEntityTest {

    @Test
    void shouldCreateNewPatientAddressEntity() {
        final var expectedStreet = "string streetVz4bE";
        final var expectedNumber = "string number5wknm";
        final var expectedNeighborhood = "string neighborhoodNBoHC";
        final var expectedCity = "string cityikH2f";
        final var expectedState = "string stateL4MEk";
        final var expectedCountry = "string countryCfG6U";
        final var expectedPatient = PatientEntity.create("string namenz3yuK", LocalDate.now(), "string genderkhzcO");

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
        final var expectedStreet = "string streetSdMBp";
        final var expectedNumber = "string numberY8Cu8";
        final var expectedNeighborhood = "string neighborhood3dTZl";
        final var expectedCity = "string cityklBHo";
        final var expectedState = "string stateSamjX";
        final var expectedCountry = "string country41RAP";

        final var actualPatientAddressEntity = PatientAddressEntity.create("string streetwxUIM", "string numberu8hkP", "string neighborhoodox7wJ",
            "string cityyfseE", "string statePJAox", "string countryXwJSo",
            PatientEntity.create("string nameRrxWUG", LocalDate.now(), "string genderlCyQ6"));
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
        final var expectedId = "string id6M5xO";
        final var expectedStreet = "string streetbIFc7";
        final var expectedNumber = "string numberfvyqw";
        final var expectedNeighborhood = "string neighborhoodDv8YV";
        final var expectedCity = "string city1UYq4";
        final var expectedState = "string stateb9iSu";
        final var expectedCountry = "string countryx7BbY";
        final var expectedPatient = PatientEntity.create("string nameEsb10h", LocalDate.now(), "string gender0Lg2T");

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
        final var expectedId = "string id41Nqp";
        final var expectedStreet = "string streetUGtBp";
        final var expectedNumber = "string number2JcRr";
        final var expectedNeighborhood = "string neighborhood6v7NS";
        final var expectedCity = "string cityFJ141";
        final var expectedState = "string state5thDM";
        final var expectedCountry = "string countryOdxTt";
        final var expectedPatient = PatientEntity.create("string nameJnEynt", LocalDate.now(), "string gender4G6yx");

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
        final var expectedStreet = "string streetF4whP";
        final var expectedNumber = "string numberRCCFg";
        final var expectedNeighborhood = "string neighborhoodhhCFM";
        final var expectedCity = "string cityKaxkm";
        final var expectedState = "string stategltFe";
        final var expectedCountry = "string country4cmHI";
        final var expectedPatient = PatientEntity.create("string nameDQ4mKm", LocalDate.now(), "string genderNbORq");

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
