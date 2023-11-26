package br.com.project.domain.treatment;

import br.com.project.domain.AuditableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "treatment")
public class TreatmentEntity extends AuditableEntity {
 
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "doctor")
    private String doctorId;
    @Column(name = "patient")
    private String patientId;
    @Column(name = "appointment")
    private String appointmentId;
    @Column(name = "diagnosis")
    private String diagnosis;
    @Column(name = "prescription")
    private String prescription;
    @Column(name = "notes")
    private String notes;
    @Column(name = "outcome")
    private String outcome;

    private TreatmentEntity(final String id,
                            final String doctorId,
                            final String patientId,
                            final String appointmentId,
                            final String diagnosis,
                            final String prescription,
                            final String notes,
                            final String outcome,
                            final LocalDateTime createdAt,
                            final LocalDateTime updatedAt,
                            final LocalDateTime deletedAt) {
        this.id = id;
        this.doctorId = Objects.requireNonNull(doctorId, "doctorId should not be null");
        this.patientId = Objects.requireNonNull(patientId, "patientId should not be null");
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId should not be null");
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
        this.outcome = outcome;
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        selfValidate();
    }

    public static TreatmentEntity create(final String doctorId,
                                         final String patientId,
                                         final String appointmentId,
                                         final String diagnosis,
                                         final String prescription,
                                         final String notes,
                                         final String outcome) {
        final var id = UUID.randomUUID().toString();
        return new TreatmentEntity(id,
            doctorId,
            patientId,
            appointmentId,
            diagnosis,
            prescription,
            notes,
            outcome, LocalDateTime.now(), null, null);
    }

    public static TreatmentEntity with(final String id,
                                       final String doctorId,
                                       final String patientId,
                                       final String appointmentId,
                                       final String diagnosis,
                                       final String prescription,
                                       final String notes,
                                       final String outcome,
                                       final LocalDateTime createdAt,
                                       final LocalDateTime updatedAt,
                                       final LocalDateTime deletedAt) {
        return new TreatmentEntity(id,
            doctorId,
            patientId,
            appointmentId,
            diagnosis,
            prescription,
            notes,
            outcome,
            createdAt,
            updatedAt,
            deletedAt);
    }

    public static TreatmentEntity with(TreatmentEntity treatmentEntity) {
        return new TreatmentEntity(treatmentEntity.getId(),
            treatmentEntity.getDoctorId(),
            treatmentEntity.getPatientId(),
            treatmentEntity.getAppointmentId(),
            treatmentEntity.getDiagnosis(),
            treatmentEntity.getPrescription(),
            treatmentEntity.getNotes(),
            treatmentEntity.getOutcome(),
            treatmentEntity.getCreatedAt(),
            treatmentEntity.getUpdatedAt(),
            treatmentEntity.getDeletedAt());
    }

    public void update(final String doctorId,
                       final String patientId,
                       final String appointmentId,
                       final String diagnosis,
                       final String prescription,
                       final String notes,
                       final String outcome) {
        this.doctorId = Objects.requireNonNull(doctorId, "doctorId should not be null");
        this.patientId = Objects.requireNonNull(patientId, "patientId should not be null");
        this.appointmentId = Objects.requireNonNull(appointmentId, "appointmentId should not be null");
        this.diagnosis = diagnosis;
        this.prescription = prescription;
        this.notes = notes;
        this.outcome = outcome;
        setUpdatedAt(LocalDateTime.now());
        selfValidate();
    }

    public void delete() {
        setDeletedAt(LocalDateTime.now());
    }

    private void selfValidate() {
    }

}
