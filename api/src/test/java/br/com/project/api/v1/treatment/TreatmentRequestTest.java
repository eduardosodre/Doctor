package br.com.project.api.v1.treatment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreatmentRequestTest {

    @Test
    void shouldToEntity() {
        final var expectedDoctorId = "5b2c37aa-9e92-4614-8e8c-9ae2a11a7ea9";
        final var expectedPatientId = "2f6c3b6e-7b87-4349-85f3-14c32802ec1b";
        final var expectedAppointmentId = "50d52232-3acd-4402-b8b2-de31e4afcf0a";
        final var expectedDiagnosis = "string diagnosisUkQVv";
        final var expectedPrescription = "string prescriptionjD558";
        final var expectedNotes = "string notes0nT96";
        final var expectedOutcome = "string outcomejeiCI";
        final var actualTreatmentRequest = new TreatmentRequest(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);

        final var entity = actualTreatmentRequest.toEntity();

        Assertions.assertEquals(expectedDoctorId, entity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, entity.getPatientId());
        Assertions.assertEquals(expectedAppointmentId, entity.getAppointmentId());
        Assertions.assertEquals(expectedDiagnosis, entity.getDiagnosis());
        Assertions.assertEquals(expectedPrescription, entity.getPrescription());
        Assertions.assertEquals(expectedNotes, entity.getNotes());
        Assertions.assertEquals(expectedOutcome, entity.getOutcome());
    }

    @Test
    void shouldToEntityWithId() {
        final var expectedId = "747bdff0-a3e0-4715-b0eb-22feadd92bd9";
        final var expectedDoctorId = "c9f204a0-0295-4c10-86a8-e795fdd0703e";
        final var expectedPatientId = "4309383b-8ee4-4de3-b7f6-3917f28f0bc1";
        final var expectedAppointmentId = "288778dc-b0fb-4edd-86de-b1a57d73f2d3";
        final var expectedDiagnosis = "string diagnosisu799E";
        final var expectedPrescription = "string prescriptionkwAyg";
        final var expectedNotes = "string notes1Vkmq";
        final var expectedOutcome = "string outcomex2cv8";
        final var actualTreatmentRequest = new TreatmentRequest(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);

        final var entity = actualTreatmentRequest.toEntity(expectedId);

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
