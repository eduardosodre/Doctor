package br.com.project.api.v1.doctor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoctorFilterRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedId = "string ideDZ9d";
        final var expectedCrm = "string crmg6d";
        final var expectedName = "string namelIdHCv";
        final var expectedSpecialty = "string specialtyKhb";
        final var actualDoctorFilterRequest = new DoctorFilterRequest(expectedId, expectedCrm, expectedName, expectedSpecialty);

        final var entity = actualDoctorFilterRequest.toEntity();

        Assertions.assertEquals(expectedId, entity.getId());
        Assertions.assertEquals(expectedCrm, entity.getCrm());
        Assertions.assertEquals(expectedName, entity.getName());
        Assertions.assertEquals(expectedSpecialty, entity.getSpecialty());
    }

}
