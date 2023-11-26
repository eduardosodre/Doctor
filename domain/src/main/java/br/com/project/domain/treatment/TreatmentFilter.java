package br.com.project.domain.treatment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TreatmentFilter {
 
    private String id;
    private String doctorId;
    private String patientId;
    private String appointmentId;
    private String diagnosis;
    private String prescription;
    private String notes;
    private String outcome;

    public static TreatmentFilter create(final String id,
                                         final String doctorId,
                                         final String patientId,
                                         final String appointmentId,
                                         final String diagnosis,
                                         final String prescription,
                                         final String notes,
                                         final String outcome) {
        return new TreatmentFilter(id,
            doctorId,
            patientId,
            appointmentId,
            diagnosis,
            prescription,
            notes,
            outcome);
    }
}
