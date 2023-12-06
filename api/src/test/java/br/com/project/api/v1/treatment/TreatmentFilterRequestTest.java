package br.com.project.api.v1.treatment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreatmentFilterRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedId = "string idWZ0YW";
        final var expectedDoctorId = "4f13fa27-b7e3-43be-b917-847348623ce2";
        final var expectedPatientId = "1f47412d-c2ba-4475-83ab-c6cbaec1cc14";
        final var expectedAppointmentId = "c7efe044-a901-4e23-a321-51644bc077f3";
        final var expectedDiagnosis = "string diagnosisn5OSa";
        final var expectedPrescription = "string prescription2M611";
        final var expectedNotes = "string notes5CKgU";
        final var expectedOutcome = "string outcomeMb7GV";
        final var actualTreatmentFilterRequest = new TreatmentFilterRequest(expectedId, expectedDoctorId, expectedPatientId, expectedAppointmentId,
            expectedDiagnosis, expectedPrescription, expectedNotes, expectedOutcome);

        final var entity = actualTreatmentFilterRequest.toEntity();

        Assertions.assertEquals(expectedId, entity.getId());
        Assertions.assertEquals(expectedDoctorId, entity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, entity.getPatientId());
        Assertions.assertEquals(expectedAppointmentId, entity.getAppointmentId());
        Assertions.assertEquals(expectedDiagnosis, entity.getDiagnosis());
        Assertions.assertEquals(expectedPrescription, entity.getPrescription());
        Assertions.assertEquals(expectedNotes, entity.getNotes());
        Assertions.assertEquals(expectedOutcome, entity.getOutcome());
    }

}
