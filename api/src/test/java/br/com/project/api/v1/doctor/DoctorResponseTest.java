package br.com.project.api.v1.doctor;

import br.com.project.domain.doctor.DoctorEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoctorResponseTest {

    @Test
    void shouldToEntity() {
        final var expectedCrm = "string crmSul";
        final var expectedName = "string namemhf63o";
        final var expectedSpecialty = "string specialtyxwb";

        final var actualDoctorEntity = DoctorEntity.create(expectedCrm, expectedName, expectedSpecialty);
        final var response = DoctorResponse.fromEntity(actualDoctorEntity);

        Assertions.assertEquals(actualDoctorEntity.getId(), response.id());
        Assertions.assertEquals(expectedCrm, response.crm());
        Assertions.assertEquals(expectedName, response.name());
        Assertions.assertEquals(expectedSpecialty, response.specialty());
    }

}
