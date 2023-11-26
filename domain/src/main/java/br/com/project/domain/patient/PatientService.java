package br.com.project.domain.patient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.stream.Collectors;
import br.com.project.domain.exceptions.DomainException;
import br.com.project.domain.validation.Error;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.project.domain.exceptions.NotFoundException;
import br.com.project.domain.patientphone.PatientPhoneService;
import br.com.project.domain.patientaddress.PatientAddressService;
import br.com.project.domain.patientemail.PatientEmailService;

@Service
@RequiredArgsConstructor
public class PatientService {

	private static final String PATIENT_NOT_FOUND = "Patient not found";
	private final PatientRepository repository;
	private final PatientPhoneService patientPhoneService;
	private final PatientAddressService patientAddressService;
	private final PatientEmailService patientEmailService;

	public PatientEntity save(final PatientEntity entity) {
		final var savedEntity = repository.save(entity);
		return savedEntity;
	}

	public PatientEntity update(final String id, final PatientEntity entity) {
        final var entityFromBd = findById(id);
        entityFromBd.update(
			entity.getName(),
			entity.getBirthdate(),
			entity.getGender());
		entityFromBd.setPhones(entity.getPhones());
		entityFromBd.setEmails(entity.getEmails());
		entityFromBd.setAddresses(entity.getAddresses());
		final var savedEntity = repository.save(entityFromBd);
		return savedEntity;
	}

	public PatientEntity findById(final String id) {
		return repository.findById(id).orElseThrow(() -> NotFoundException.create(PATIENT_NOT_FOUND));
	}

	public List<PatientEntity> findByIds(final List<String> ids) {
		return repository.findAllById(ids);
	}

	public Page<PatientEntity> findAll(final PatientFilter filter, final Pageable pageable) {
		return repository.findAll(PatientSpecifications.filter(filter), pageable);
	}

	public void delete(final String id) {
		final var optionalPatient = repository.findById(id);
		optionalPatient.ifPresent(entityPosition -> {
			entityPosition.delete();
			repository.save(entityPosition);
		});
	}

    @Transactional
    public void deletePatientPhone(final String id,
                                    final String patientPhoneId) {
        final var entity = findById(id);
        patientPhoneService.delete(patientPhoneId);
    }

    @Transactional
    public void deletePatientAddress(final String id,
                                    final String patientAddressId) {
        final var entity = findById(id);
        patientAddressService.delete(patientAddressId);
    }

    @Transactional
    public void deletePatientEmail(final String id,
                                    final String patientEmailId) {
        final var entity = findById(id);
        patientEmailService.delete(patientEmailId);
    }
}
