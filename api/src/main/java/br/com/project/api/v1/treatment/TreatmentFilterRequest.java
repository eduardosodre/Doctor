package br.com.project.api.v1.treatment;

import br.com.project.domain.treatment.TreatmentFilter;

public record TreatmentFilterRequest(String id,
                                     String doctorId,
                                     String patientId,
                                     String appointmentId,
                                     String diagnosis,
                                     String prescription,
                                     String notes,
                                     String outcome) {

    public TreatmentFilter toEntity() {
        return TreatmentFilter.create(id,
            doctorId,
            patientId,
            appointmentId,
            diagnosis,
            prescription,
            notes,
            outcome);
    }

}
