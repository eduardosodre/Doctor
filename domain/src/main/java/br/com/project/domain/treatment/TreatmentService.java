package br.com.project.domain.treatment;

import br.com.project.domain.appointment.AppointmentService;
import br.com.project.domain.doctor.DoctorService;
import br.com.project.domain.exceptions.NotFoundException;
import br.com.project.domain.patient.PatientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TreatmentService {
 
    private static final String TREATMENT_NOT_FOUND = "Treatment not found";
    private final TreatmentRepository repository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final AppointmentService appointmentService;

    public TreatmentEntity save(final TreatmentEntity entity) {
        validateAggregations(entity);
        final var savedEntity = repository.save(entity);
        return savedEntity;
    }

    public TreatmentEntity update(final String id, final TreatmentEntity entity) {
        final var entityFromBd = findById(id);
        validateAggregations(entity);
        entityFromBd.update(
            entity.getDoctorId(),
            entity.getPatientId(),
            entity.getAppointmentId(),
            entity.getDiagnosis(),
            entity.getPrescription(),
            entity.getNotes(),
            entity.getOutcome());
        final var savedEntity = repository.save(entityFromBd);
        return savedEntity;
    }

    public TreatmentEntity findById(final String id) {
        return repository.findById(id).orElseThrow(() -> NotFoundException.create(TREATMENT_NOT_FOUND));
    }

    public List<TreatmentEntity> findByIds(final List<String> ids) {
        return repository.findAllById(ids);
    }

    public Page<TreatmentEntity> findAll(final TreatmentFilter filter, final Pageable pageable) {
        return repository.findAll(TreatmentSpecifications.filter(filter), pageable);
    }

    public void delete(final String id) {
        final var optionalTreatment = repository.findById(id);
        optionalTreatment.ifPresent(entityPosition -> {
            entityPosition.delete();
            repository.save(entityPosition);
        });
    }

    private void validateAggregations(final TreatmentEntity entity) {
        if (StringUtils.isNotEmpty(entity.getDoctorId())) {
            doctorService.findById(entity.getDoctorId());
        }
        if (StringUtils.isNotEmpty(entity.getPatientId())) {
            patientService.findById(entity.getPatientId());
        }
        if (StringUtils.isNotEmpty(entity.getAppointmentId())) {
            appointmentService.findById(entity.getAppointmentId());
        }
    }

}
