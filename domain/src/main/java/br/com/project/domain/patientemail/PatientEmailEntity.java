package br.com.project.domain.patientemail;

import br.com.project.domain.AuditableEntity;
import br.com.project.domain.patient.PatientEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "patient_email")
public class PatientEmailEntity extends AuditableEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "patient_entity_id")
    private PatientEntity patient;

    private PatientEmailEntity(final String id,
                               final String email,
                               final PatientEntity patient,
                               final LocalDateTime createdAt,
                               final LocalDateTime updatedAt,
                               final LocalDateTime deletedAt) {
        this.id = id;
        this.email = email;
        this.patient = Objects.requireNonNull(patient, "patient should not be null");
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        selfValidate();
    }

    public static PatientEmailEntity create(final String email,
                                            final PatientEntity patient) {
        final var id = UUID.randomUUID().toString();
        return new PatientEmailEntity(id,
            email,
            patient, LocalDateTime.now(), null, null);
    }

    public static PatientEmailEntity with(final String id,
                                          final String email,
                                          final PatientEntity patient,
                                          final LocalDateTime createdAt,
                                          final LocalDateTime updatedAt,
                                          final LocalDateTime deletedAt) {
        return new PatientEmailEntity(id,
            email,
            patient,
            createdAt,
            updatedAt,
            deletedAt);
    }

    public static PatientEmailEntity with(PatientEmailEntity patientEmailEntity) {
        return new PatientEmailEntity(patientEmailEntity.getId(),
            patientEmailEntity.getEmail(),
            patientEmailEntity.getPatient(),
            patientEmailEntity.getCreatedAt(),
            patientEmailEntity.getUpdatedAt(),
            patientEmailEntity.getDeletedAt());
    }

    public void update(final String email) {
        this.email = email;
        setUpdatedAt(LocalDateTime.now());
        selfValidate();
    }

    public void delete() {
        setDeletedAt(LocalDateTime.now());
    }

    private void selfValidate() {
    }

}
