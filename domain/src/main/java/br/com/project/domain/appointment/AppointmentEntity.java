package br.com.project.domain.appointment;

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
@Table(name = "appointment")
public class AppointmentEntity extends AuditableEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "doctor")
    private String doctorId;
    @Column(name = "patient")
    private String patientId;
    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;
    @Column(name = "status")
    private String status;

    private AppointmentEntity(final String id,
                              final String doctorId,
                              final String patientId,
                              final LocalDateTime appointmentDate,
                              final String status,
                              final LocalDateTime createdAt,
                              final LocalDateTime updatedAt,
                              final LocalDateTime deletedAt) {
        this.id = id;
        this.doctorId = Objects.requireNonNull(doctorId, "doctorId should not be null");
        this.patientId = Objects.requireNonNull(patientId, "patientId should not be null");
        this.appointmentDate = Objects.requireNonNull(appointmentDate, "appointmentDate should not be null");
        this.status = Objects.requireNonNull(status, "status should not be null");
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        selfValidate();
    }

    public static AppointmentEntity create(final String doctorId,
                                           final String patientId,
                                           final LocalDateTime appointmentDate,
                                           final String status) {
        final var id = UUID.randomUUID().toString();
        return new AppointmentEntity(id,
            doctorId,
            patientId,
            appointmentDate,
            status, LocalDateTime.now(), null, null);
    }

    public static AppointmentEntity with(final String id,
                                         final String doctorId,
                                         final String patientId,
                                         final LocalDateTime appointmentDate,
                                         final String status,
                                         final LocalDateTime createdAt,
                                         final LocalDateTime updatedAt,
                                         final LocalDateTime deletedAt) {
        return new AppointmentEntity(id,
            doctorId,
            patientId,
            appointmentDate,
            status,
            createdAt,
            updatedAt,
            deletedAt);
    }

    public static AppointmentEntity with(AppointmentEntity appointmentEntity) {
        return new AppointmentEntity(appointmentEntity.getId(),
            appointmentEntity.getDoctorId(),
            appointmentEntity.getPatientId(),
            appointmentEntity.getAppointmentDate(),
            appointmentEntity.getStatus(),
            appointmentEntity.getCreatedAt(),
            appointmentEntity.getUpdatedAt(),
            appointmentEntity.getDeletedAt());
    }

    public void update(final String doctorId,
                       final String patientId,
                       final LocalDateTime appointmentDate,
                       final String status) {
        this.doctorId = Objects.requireNonNull(doctorId, "doctorId should not be null");
        this.patientId = Objects.requireNonNull(patientId, "patientId should not be null");
        this.appointmentDate = Objects.requireNonNull(appointmentDate, "appointmentDate should not be null");
        this.status = Objects.requireNonNull(status, "status should not be null");
        setUpdatedAt(LocalDateTime.now());
        selfValidate();
    }

    public void delete() {
        setDeletedAt(LocalDateTime.now());
    }

    private void selfValidate() {
    }

}
