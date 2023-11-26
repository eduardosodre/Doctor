package br.com.project.api.v1.treatment;

import br.com.project.domain.treatment.TreatmentEntity;

public record TreatmentResponse(String id,
                                String doctorId,
                                String patientId,
                                String appointmentId,
                                String diagnosis,
                                String prescription,
                                String notes,
                                String outcome) {

    public static TreatmentResponse fromEntity(TreatmentEntity entity) {
        return new TreatmentResponse(
            entity.getId(),
            entity.getDoctorId(),
            entity.getPatientId(),
            entity.getAppointmentId(),
            entity.getDiagnosis(),
            entity.getPrescription(),
            entity.getNotes(),
            entity.getOutcome());
    }
}
