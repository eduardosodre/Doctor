package br.com.project.api.v1.treatment;

import br.com.project.domain.treatment.TreatmentEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreatmentResponseTest {

    @Test
    void shouldToEntity() {
        final var expectedDoctorId = "bfb5dd1f-512f-4a34-81b3-03ad96ea9ed6";
        final var expectedPatientId = "07f30d1c-1f96-4b7d-8f0a-a1b6025ce6e5";
        final var expectedAppointmentId = "7c49a191-1835-4a66-b151-2f5ff774842c";
        final var expectedDiagnosis = "string diagnosisMTo4r";
        final var expectedPrescription = "string prescriptionSCLBo";
        final var expectedNotes = "string notesfa33b";
        final var expectedOutcome = "string outcomem3xz3";

        final var actualTreatmentEntity = TreatmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);
        final var response = TreatmentResponse.fromEntity(actualTreatmentEntity);

        Assertions.assertEquals(actualTreatmentEntity.getId(), response.id());
        Assertions.assertEquals(expectedDoctorId, response.doctorId());
        Assertions.assertEquals(expectedPatientId, response.patientId());
        Assertions.assertEquals(expectedAppointmentId, response.appointmentId());
        Assertions.assertEquals(expectedDiagnosis, response.diagnosis());
        Assertions.assertEquals(expectedPrescription, response.prescription());
        Assertions.assertEquals(expectedNotes, response.notes());
        Assertions.assertEquals(expectedOutcome, response.outcome());
    }

}
