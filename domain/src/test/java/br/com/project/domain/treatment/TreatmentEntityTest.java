package br.com.project.domain.treatment;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TreatmentEntityTest {
 
    @Test
    void shouldCreateNewTreatmentEntity() {
        final var expectedDoctorId = "b2eccd15-32fa-48e8-9dfa-0c51291d4746";
        final var expectedPatientId = "76f7b769-44c4-4452-8b13-593098747d48";
        final var expectedAppointmentId = "8fbe2df7-2df3-4ac4-810c-fc200216e230";
        final var expectedDiagnosis = "string diagnosisOR2s6";
        final var expectedPrescription = "string prescriptionDqOHN";
        final var expectedNotes = "string notesm0Wjz";
        final var expectedOutcome = "string outcomeq2Pkn";

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
        final var expectedDoctorId = "72f2cf3a-8cf5-4b0f-8ff8-b73eba3a0617";
        final var expectedPatientId = "1148b4ba-96c0-4df8-91af-e68ddd0353e1";
        final var expectedAppointmentId = "3615d90b-9937-4cbd-8979-325c43f1dd38";
        final var expectedDiagnosis = "string diagnosis8OxmF";
        final var expectedPrescription = "string prescriptionjLmSl";
        final var expectedNotes = "string notesJa4y9";
        final var expectedOutcome = "string outcomeV0eaz";

        final var actualTreatmentEntity = TreatmentEntity.create("08297432-ee09-4c7d-b93a-7f19799dd506", "4c8ff618-1618-4aad-9b10-c983964bcf74",
            "763b1f39-c637-4852-ae10-03a2a70a1a39", "string diagnosisVCCP6", "string prescriptioni6FIe", "string notesAA0xG", "string outcomeXMgvF");
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
        final var expectedId = "string idTgbK8";
        final var expectedDoctorId = "a1020e0c-c7ef-476f-b688-b7cc7ccf7d53";
        final var expectedPatientId = "caef6be0-8a5f-46ce-9a69-570e0257d8ba";
        final var expectedAppointmentId = "ddb8f7aa-6cbb-4717-a4b3-bc2ef51787f7";
        final var expectedDiagnosis = "string diagnosisaMsaz";
        final var expectedPrescription = "string prescriptionI37df";
        final var expectedNotes = "string noteskpwKa";
        final var expectedOutcome = "string outcomeGsafy";

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
        final var expectedId = "string idpVZwX";
        final var expectedDoctorId = "2bfeda8a-5489-4403-9dc2-2d111cc60bf8";
        final var expectedPatientId = "0191aa59-e528-4b07-a5ef-772f41c789f3";
        final var expectedAppointmentId = "25402d1a-497c-496f-87fe-21250aa20289";
        final var expectedDiagnosis = "string diagnosisUMbss";
        final var expectedPrescription = "string prescriptionhlmrv";
        final var expectedNotes = "string notes0Cnlr";
        final var expectedOutcome = "string outcomegEs8h";

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
        final var expectedDoctorId = "f39d5413-19dd-4bcd-9bb1-33864ebb6452";
        final var expectedPatientId = "3feecdfa-cd8f-41c0-89e4-8bfd684985ff";
        final var expectedAppointmentId = "0759093e-8951-4e26-8ad3-7a69f4822cdf";
        final var expectedDiagnosis = "string diagnosissFpER";
        final var expectedPrescription = "string prescription2q0GO";
        final var expectedNotes = "string notesqSMHf";
        final var expectedOutcome = "string outcomeSwaaW";

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
