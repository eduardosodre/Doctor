package br.com.project.domain.doctor;

import br.com.project.domain.AuditableEntity;
import br.com.project.domain.exceptions.NotificationException;
import br.com.project.domain.validation.Error;
import br.com.project.domain.validation.handler.Notification;
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
@Table(name = "doctor")
public class DoctorEntity extends AuditableEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "crm")
    private String crm;
    @Column(name = "name")
    private String name;
    @Column(name = "specialty")
    private String specialty;

    private DoctorEntity(final String id,
                         final String crm,
                         final String name,
                         final String specialty,
                         final LocalDateTime createdAt,
                         final LocalDateTime updatedAt,
                         final LocalDateTime deletedAt) {
        this.id = id;
        this.crm = Objects.requireNonNull(crm, "crm should not be null");
        this.name = Objects.requireNonNull(name, "name should not be null");
        this.specialty = Objects.requireNonNull(specialty, "specialty should not be null");
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        selfValidate();
    }

    public static DoctorEntity create(final String crm,
                                      final String name,
                                      final String specialty) {
        final var id = UUID.randomUUID().toString();
        return new DoctorEntity(id,
            crm,
            name,
            specialty, LocalDateTime.now(), null, null);
    }

    public static DoctorEntity with(final String id,
                                    final String crm,
                                    final String name,
                                    final String specialty,
                                    final LocalDateTime createdAt,
                                    final LocalDateTime updatedAt,
                                    final LocalDateTime deletedAt) {
        return new DoctorEntity(id,
            crm,
            name,
            specialty,
            createdAt,
            updatedAt,
            deletedAt);
    }

    public static DoctorEntity with(DoctorEntity doctorEntity) {
        return new DoctorEntity(doctorEntity.getId(),
            doctorEntity.getCrm(),
            doctorEntity.getName(),
            doctorEntity.getSpecialty(),
            doctorEntity.getCreatedAt(),
            doctorEntity.getUpdatedAt(),
            doctorEntity.getDeletedAt());
    }

    public void update(final String crm,
                       final String name,
                       final String specialty) {
        this.crm = Objects.requireNonNull(crm, "crm should not be null");
        this.name = Objects.requireNonNull(name, "name should not be null");
        this.specialty = Objects.requireNonNull(specialty, "specialty should not be null");
        setUpdatedAt(LocalDateTime.now());
        selfValidate();
    }

    public void delete() {
        setDeletedAt(LocalDateTime.now());
    }

    private void selfValidate() {
        final var notification = Notification.create();
        final var crmLength = crm.trim().length();
        if (crmLength <= 3 || crmLength >= 20) {
            notification.append(new Error("crm must be between 3 and 20 characters"));
        }

        final var nameLength = name.trim().length();
        if (nameLength <= 6 || nameLength >= 200) {
            notification.append(new Error("name must be between 6 and 200 characters"));
        }

        final var specialtyLength = specialty.trim().length();
        if (specialtyLength <= 3 || specialtyLength >= 100) {
            notification.append(new Error("specialty must be between 3 and 100 characters"));
        }

        if (notification.hasError()) {
            throw new NotificationException("Failed to validate DoctorEntity", notification);
        }
    }

}
