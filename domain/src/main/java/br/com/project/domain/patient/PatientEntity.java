package br.com.project.domain.patient;

import br.com.project.domain.AuditableEntity;
import br.com.project.domain.validation.handler.Notification;
import br.com.project.domain.exceptions.NotificationException;
import br.com.project.domain.validation.Error;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import br.com.project.domain.patientphone.PatientPhoneEntity;
import br.com.project.domain.patientaddress.PatientAddressEntity;
import br.com.project.domain.patientemail.PatientEmailEntity;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "patient")
public class PatientEntity extends AuditableEntity {

	@Id
	@Column(columnDefinition = "VARCHAR(36)")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "birthdate")
	private LocalDate birthdate;
	@Column(name = "gender")
	private String gender;
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientPhoneEntity> phones = new ArrayList<>();
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientAddressEntity> addresses = new ArrayList<>();
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PatientEmailEntity> emails = new ArrayList<>();

	public void setPhones(final List<PatientPhoneEntity> phones) {
		this.phones.clear();
		if(phones != null) {
			this.phones.addAll(phones);
		}
	}
	public void setAddresses(final List<PatientAddressEntity> addresses) {
		this.addresses.clear();
		if(addresses != null) {
			this.addresses.addAll(addresses);
		}
	}
	public void setEmails(final List<PatientEmailEntity> emails) {
		this.emails.clear();
		if(emails != null) {
			this.emails.addAll(emails);
		}
	}
	private PatientEntity(					final String id,
					final String name,
					final LocalDate birthdate,
					final String gender,
					final LocalDateTime createdAt,
					final LocalDateTime updatedAt,
					final LocalDateTime deletedAt) {
		this.id = id;
		this.name = Objects.requireNonNull(name, "name should not be null");
		this.birthdate = birthdate;
		this.gender = gender;
		setCreatedAt(createdAt);
		setUpdatedAt(updatedAt);
		setDeletedAt(deletedAt);
		selfValidate();
	}

	public static PatientEntity create(					final String name,
					final LocalDate birthdate,
					final String gender) {
	final var id = UUID.randomUUID().toString();
	return new PatientEntity(		id,
		name,
		birthdate,
		gender, LocalDateTime.now(), null, null);
	}

	public static PatientEntity with(					final String id,
					final String name,
					final LocalDate birthdate,
					final String gender,
					final LocalDateTime createdAt,
					final LocalDateTime updatedAt,
					final LocalDateTime deletedAt) {
	return new PatientEntity(		id,
		name,
		birthdate,
		gender,
		createdAt,
		updatedAt,
		deletedAt);
	}

	public static PatientEntity with(PatientEntity patientEntity) {
	return new PatientEntity(		patientEntity.getId(),
		patientEntity.getName(),
		patientEntity.getBirthdate(),
		patientEntity.getGender(),
		patientEntity.getCreatedAt(),
		patientEntity.getUpdatedAt(),
		patientEntity.getDeletedAt());
	}

	public void update(					final String name,
					final LocalDate birthdate,
					final String gender) {
		this.name = Objects.requireNonNull(name, "name should not be null");
		this.birthdate = birthdate;
		this.gender = gender;
		setUpdatedAt(LocalDateTime.now());
		selfValidate();
	}

	public void delete() {
		setDeletedAt(LocalDateTime.now());
	}

	private void selfValidate() {
		final var notification = Notification.create();		
		final var nameLength = name.trim().length();
		if (nameLength <= 6 || nameLength >= 200) {
			notification.append(new Error("name must be between 6 and 200 characters"));
		}

		if (notification.hasError()) {
			throw new NotificationException("Failed to validate PatientEntity", notification);
		}
	}

}
