package br.com.project.domain.patientaddress;

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
@Table(name = "patientAddress")
public class PatientAddressEntity extends AuditableEntity {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;
    @Column(name = "street")
    private String street;
    @Column(name = "number")
    private String number;
    @Column(name = "neighborhood")
    private String neighborhood;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;

    @ManyToOne
    @JoinColumn(name = "patient_entity_id")
    private PatientEntity patient;

    private PatientAddressEntity(final String id,
                                 final String street,
                                 final String number,
                                 final String neighborhood,
                                 final String city,
                                 final String state,
                                 final String country,
                                 final PatientEntity patient,
                                 final LocalDateTime createdAt,
                                 final LocalDateTime updatedAt,
                                 final LocalDateTime deletedAt) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.patient = Objects.requireNonNull(patient, "patient should not be null");
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
        setDeletedAt(deletedAt);
        selfValidate();
    }

    public static PatientAddressEntity create(final String street,
                                              final String number,
                                              final String neighborhood,
                                              final String city,
                                              final String state,
                                              final String country,
                                              final PatientEntity patient) {
        final var id = UUID.randomUUID().toString();
        return new PatientAddressEntity(id,
            street,
            number,
            neighborhood,
            city,
            state,
            country,
            patient, LocalDateTime.now(), null, null);
    }

    public static PatientAddressEntity with(final String id,
                                            final String street,
                                            final String number,
                                            final String neighborhood,
                                            final String city,
                                            final String state,
                                            final String country,
                                            final PatientEntity patient,
                                            final LocalDateTime createdAt,
                                            final LocalDateTime updatedAt,
                                            final LocalDateTime deletedAt) {
        return new PatientAddressEntity(id,
            street,
            number,
            neighborhood,
            city,
            state,
            country,
            patient,
            createdAt,
            updatedAt,
            deletedAt);
    }

    public static PatientAddressEntity with(PatientAddressEntity patientAddressEntity) {
        return new PatientAddressEntity(patientAddressEntity.getId(),
            patientAddressEntity.getStreet(),
            patientAddressEntity.getNumber(),
            patientAddressEntity.getNeighborhood(),
            patientAddressEntity.getCity(),
            patientAddressEntity.getState(),
            patientAddressEntity.getCountry(),
            patientAddressEntity.getPatient(),
            patientAddressEntity.getCreatedAt(),
            patientAddressEntity.getUpdatedAt(),
            patientAddressEntity.getDeletedAt());
    }

    public void update(final String street,
                       final String number,
                       final String neighborhood,
                       final String city,
                       final String state,
                       final String country) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        setUpdatedAt(LocalDateTime.now());
        selfValidate();
    }

    public void delete() {
        setDeletedAt(LocalDateTime.now());
    }

    private void selfValidate() {
    }

}
