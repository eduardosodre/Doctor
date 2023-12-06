package br.com.project.api.v1.doctor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoctorRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedCrm = "string crmslw";
        final var expectedName = "string nameSlCujj";
        final var expectedSpecialty = "string specialtyJeU";
        final var actualDoctorRequest = new DoctorRequest(expectedCrm, expectedName, expectedSpecialty);

        final var entity = actualDoctorRequest.toEntity();

        Assertions.assertEquals(expectedCrm, entity.getCrm());
        Assertions.assertEquals(expectedName, entity.getName());
        Assertions.assertEquals(expectedSpecialty, entity.getSpecialty());
    }

    @Test
    void shouldToEntityWithId() {
        final var expectedId = "41305887-1256-4e3e-8b62-89f86f5a1f0e";
        final var expectedCrm = "string crmMD2";
        final var expectedName = "string namejTH2NR";
        final var expectedSpecialty = "string specialtyjUs";
        final var actualDoctorRequest = new DoctorRequest(expectedCrm, expectedName, expectedSpecialty);

        final var entity = actualDoctorRequest.toEntity(expectedId);

        Assertions.assertEquals(expectedId, entity.getId());
        Assertions.assertEquals(expectedCrm, entity.getCrm());
        Assertions.assertEquals(expectedName, entity.getName());
        Assertions.assertEquals(expectedSpecialty, entity.getSpecialty());
    }
}
