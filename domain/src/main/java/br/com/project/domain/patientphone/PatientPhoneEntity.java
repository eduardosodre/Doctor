package br.com.project.domain.patientphone;

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
@Table(name = "patient_phone")
public class PatientPhoneEntity extends AuditableEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "patient_entity_id")
    private PatientEntity patient;

    private PatientPhoneEntity(final String id,
                               final String phone,
                               final PatientEntity patient,
                               final LocalDateTime createdAt,
                               final LocalDateTime updatedAt,
                               final LocalDateTime deletedAt) {
        this.id = id;
        this.phone = phone;
        this.patient = Objects.requireNonNull(patient, "patient should not be null");
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        selfValidate();
    }

    public static PatientPhoneEntity create(final String phone,
                                            final PatientEntity patient) {
        final var id = UUID.randomUUID().toString();
        return new PatientPhoneEntity(id,
            phone,
            patient, LocalDateTime.now(), null, null);
    }

    public static PatientPhoneEntity with(final String id,
                                          final String phone,
                                          final PatientEntity patient,
                                          final LocalDateTime createdAt,
                                          final LocalDateTime updatedAt,
                                          final LocalDateTime deletedAt) {
        return new PatientPhoneEntity(id,
            phone,
            patient,
            createdAt,
            updatedAt,
            deletedAt);
    }

    public static PatientPhoneEntity with(PatientPhoneEntity patientPhoneEntity) {
        return new PatientPhoneEntity(patientPhoneEntity.getId(),
            patientPhoneEntity.getPhone(),
            patientPhoneEntity.getPatient(),
            patientPhoneEntity.getCreatedAt(),
            patientPhoneEntity.getUpdatedAt(),
            patientPhoneEntity.getDeletedAt());
    }

    public void update(final String phone) {
        this.phone = phone;
        setUpdatedAt(LocalDateTime.now());
        selfValidate();
    }

    public void delete() {
        setDeletedAt(LocalDateTime.now());
    }

    private void selfValidate() {
    }

}
