package br.com.project.domain.treatment;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreatmentEntityTest {

    @Test
    void shouldCreateNewTreatmentEntity() {
        final var expectedDoctorId = "78596289-befa-4f84-bed3-6133f4afa3c5";
        final var expectedPatientId = "fecf2720-517a-4069-bdcc-b543ca7af916";
        final var expectedAppointmentId = "ad61cb09-3e63-4001-baa9-a0c0f4678e75";
        final var expectedDiagnosis = "string diagnosisOHGxt";
        final var expectedPrescription = "string prescriptionC5YgK";
        final var expectedNotes = "string notesNjr8K";
        final var expectedOutcome = "string outcomeZyMST";

        final var actualTreatmentEntity = TreatmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);
        Assertions.assertNotNull(actualTreatmentEntity);
        Assertions.assertNotNull(actualTreatmentEntity.getId());
        Assertions.assertNotNull(actualTreatmentEntity.getCreatedAt());
        Assertions.assertNull(actualTreatmentEntity.getUpdatedAt());
        Assertions.assertNull(actualTreatmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualTreatmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualTreatmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentId, actualTreatmentEntity.getAppointmentId());
        Assertions.assertEquals(expectedDiagnosis, actualTreatmentEntity.getDiagnosis());
        Assertions.assertEquals(expectedPrescription, actualTreatmentEntity.getPrescription());
        Assertions.assertEquals(expectedNotes, actualTreatmentEntity.getNotes());
        Assertions.assertEquals(expectedOutcome, actualTreatmentEntity.getOutcome());
    }

    @Test
    void shouldCreateNewTreatmentEntityAndUpdate() {
        final var expectedDoctorId = "a47a1adc-02fd-4848-b0c3-814d5ba6c3a1";
        final var expectedPatientId = "d306fff7-dadb-4068-aae9-ff626512af34";
        final var expectedAppointmentId = "a75cb158-ca9c-4b0a-805f-63b6a28a825e";
        final var expectedDiagnosis = "string diagnosisox93X";
        final var expectedPrescription = "string prescription7Oeos";
        final var expectedNotes = "string notesV4Ubj";
        final var expectedOutcome = "string outcome2bYip";

        final var actualTreatmentEntity = TreatmentEntity.create("d9ee0853-b554-491f-8837-1f0c718b2f29", "8dd7f1f7-b0a8-4938-822b-79808f226136",
            "307668eb-65c9-461f-8f18-ffab8fdcc5a4", "string diagnosisROtrB", "string prescription5d5xz", "string notes1QtXd", "string outcome6xhjd");
        final var id = actualTreatmentEntity.getId();

        actualTreatmentEntity.update(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis, expectedPrescription,
            expectedNotes, expectedOutcome);

        Assertions.assertNotNull(actualTreatmentEntity);
        Assertions.assertEquals(id, actualTreatmentEntity.getId());
        Assertions.assertNotNull(actualTreatmentEntity.getCreatedAt());
        Assertions.assertNotNull(actualTreatmentEntity.getUpdatedAt());
        Assertions.assertNull(actualTreatmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualTreatmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualTreatmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentId, actualTreatmentEntity.getAppointmentId());
        Assertions.assertEquals(expectedDiagnosis, actualTreatmentEntity.getDiagnosis());
        Assertions.assertEquals(expectedPrescription, actualTreatmentEntity.getPrescription());
        Assertions.assertEquals(expectedNotes, actualTreatmentEntity.getNotes());
        Assertions.assertEquals(expectedOutcome, actualTreatmentEntity.getOutcome());
    }

    @Test
    void shouldCopyWithTreatmentEntity() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idU0JVB";
        final var expectedDoctorId = "98df57fd-3fcf-4326-b28f-3867fe94d2bd";
        final var expectedPatientId = "834a832b-be51-4ee2-a323-45104ef9d5c4";
        final var expectedAppointmentId = "9d2820b7-90a0-424b-8a72-014ae25a3058";
        final var expectedDiagnosis = "string diagnosis5z1I4";
        final var expectedPrescription = "string prescriptionaumUK";
        final var expectedNotes = "string notes4TdBx";
        final var expectedOutcome = "string outcome8x79w";

        final var actualTreatmentEntity = TreatmentEntity.with(expectedId, expectedDoctorId, expectedPatientId, expectedAppointmentId,
            expectedDiagnosis, expectedPrescription, expectedNotes, expectedOutcome, expectedCreated, expectedUpdated, expectedDeleted);
        Assertions.assertNotNull(actualTreatmentEntity);
        Assertions.assertNotNull(actualTreatmentEntity.getId());
        Assertions.assertNotNull(actualTreatmentEntity.getCreatedAt());
        Assertions.assertNotNull(actualTreatmentEntity.getUpdatedAt());
        Assertions.assertNotNull(actualTreatmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualTreatmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualTreatmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentId, actualTreatmentEntity.getAppointmentId());
        Assertions.assertEquals(expectedDiagnosis, actualTreatmentEntity.getDiagnosis());
        Assertions.assertEquals(expectedPrescription, actualTreatmentEntity.getPrescription());
        Assertions.assertEquals(expectedNotes, actualTreatmentEntity.getNotes());
        Assertions.assertEquals(expectedOutcome, actualTreatmentEntity.getOutcome());
    }

    @Test
    void shouldCopyWithTreatmentEntityFromObject() {
        final var expectedCreated = LocalDateTime.now();
        final var expectedUpdated = LocalDateTime.now();
        final var expectedDeleted = LocalDateTime.now();
        final var expectedId = "string idt4Aqy";
        final var expectedDoctorId = "5b9f5c14-0e6b-4f14-b305-d70c09c4423b";
        final var expectedPatientId = "fd43cf28-0092-4395-949c-022afc772e77";
        final var expectedAppointmentId = "d65737b0-6b89-407f-8f5e-8ae29ea5633d";
        final var expectedDiagnosis = "string diagnosisWksnA";
        final var expectedPrescription = "string prescriptionjmDJ0";
        final var expectedNotes = "string notesBX2sF";
        final var expectedOutcome = "string outcomeM5YO6";

        final var actualTreatmentEntity = TreatmentEntity.with(expectedId, expectedDoctorId, expectedPatientId, expectedAppointmentId,
            expectedDiagnosis, expectedPrescription, expectedNotes, expectedOutcome, expectedCreated, expectedUpdated, expectedDeleted);
        final var copyFromTreatmentEntity = TreatmentEntity.with(actualTreatmentEntity);
        Assertions.assertNotNull(actualTreatmentEntity);
        Assertions.assertNotNull(actualTreatmentEntity.getId());
        Assertions.assertNotNull(actualTreatmentEntity.getCreatedAt());
        Assertions.assertNotNull(actualTreatmentEntity.getUpdatedAt());
        Assertions.assertNotNull(actualTreatmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualTreatmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualTreatmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentId, actualTreatmentEntity.getAppointmentId());
        Assertions.assertEquals(expectedDiagnosis, actualTreatmentEntity.getDiagnosis());
        Assertions.assertEquals(expectedPrescription, actualTreatmentEntity.getPrescription());
        Assertions.assertEquals(expectedNotes, actualTreatmentEntity.getNotes());
        Assertions.assertEquals(expectedOutcome, actualTreatmentEntity.getOutcome());
        Assertions.assertEquals(actualTreatmentEntity.getDoctorId(), copyFromTreatmentEntity.getDoctorId());
        Assertions.assertEquals(actualTreatmentEntity.getPatientId(), copyFromTreatmentEntity.getPatientId());
        Assertions.assertEquals(actualTreatmentEntity.getAppointmentId(), copyFromTreatmentEntity.getAppointmentId());
        Assertions.assertEquals(actualTreatmentEntity.getDiagnosis(), copyFromTreatmentEntity.getDiagnosis());
        Assertions.assertEquals(actualTreatmentEntity.getPrescription(), copyFromTreatmentEntity.getPrescription());
        Assertions.assertEquals(actualTreatmentEntity.getNotes(), copyFromTreatmentEntity.getNotes());
        Assertions.assertEquals(actualTreatmentEntity.getOutcome(), copyFromTreatmentEntity.getOutcome());
        Assertions.assertEquals(actualTreatmentEntity.getCreatedAt(), copyFromTreatmentEntity.getCreatedAt());
        Assertions.assertEquals(actualTreatmentEntity.getUpdatedAt(), copyFromTreatmentEntity.getUpdatedAt());
        Assertions.assertEquals(actualTreatmentEntity.getDeletedAt(), copyFromTreatmentEntity.getDeletedAt());
    }

    @Test
    void shouldCreateNewTreatmentEntityAndDelete() {
        final var expectedDoctorId = "c80dcea5-3d8e-4198-91ba-5d712825312f";
        final var expectedPatientId = "846059b9-9b7f-4dfd-8867-e232f3223c29";
        final var expectedAppointmentId = "379323c7-b1c4-4483-b2f0-16554c216eba";
        final var expectedDiagnosis = "string diagnosislT3oO";
        final var expectedPrescription = "string prescriptionE6I9l";
        final var expectedNotes = "string notesX9qpL";
        final var expectedOutcome = "string outcomeJIUTh";

        final var actualTreatmentEntity = TreatmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);
        actualTreatmentEntity.delete();
        Assertions.assertNotNull(actualTreatmentEntity.getDeletedAt());
        Assertions.assertEquals(expectedDoctorId, actualTreatmentEntity.getDoctorId());
        Assertions.assertEquals(expectedPatientId, actualTreatmentEntity.getPatientId());
        Assertions.assertEquals(expectedAppointmentId, actualTreatmentEntity.getAppointmentId());
        Assertions.assertEquals(expectedDiagnosis, actualTreatmentEntity.getDiagnosis());
        Assertions.assertEquals(expectedPrescription, actualTreatmentEntity.getPrescription());
        Assertions.assertEquals(expectedNotes, actualTreatmentEntity.getNotes());
        Assertions.assertEquals(expectedOutcome, actualTreatmentEntity.getOutcome());
    }
}
