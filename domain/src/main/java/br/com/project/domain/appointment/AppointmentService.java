package br.com.project.domain.appointment;

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
public class AppointmentService {

    private static final String APPOINTMENT_NOT_FOUND = "Appointment not found";
    private final AppointmentRepository repository;
    private final DoctorService doctorService;
    private final PatientService patientService;

    public AppointmentEntity save(final AppointmentEntity entity) {
        validateAggregations(entity);
        final var savedEntity = repository.save(entity);
        return savedEntity;
    }

    public AppointmentEntity update(final String id, final AppointmentEntity entity) {
        final var entityFromBd = findById(id);
        validateAggregations(entity);
        entityFromBd.update(
            entity.getDoctorId(),
            entity.getPatientId(),
            entity.getAppointmentDate(),
            entity.getStatus());
        final var savedEntity = repository.save(entityFromBd);
        return savedEntity;
    }

    public AppointmentEntity findById(final String id) {
        return repository.findById(id).orElseThrow(() -> NotFoundException.create(APPOINTMENT_NOT_FOUND));
    }

    public List<AppointmentEntity> findByIds(final List<String> ids) {
        return repository.findAllById(ids);
    }

    public Page<AppointmentEntity> findAll(final AppointmentFilter filter, final Pageable pageable) {
        return repository.findAll(AppointmentSpecifications.filter(filter), pageable);
    }

    public void delete(final String id) {
        final var optionalAppointment = repository.findById(id);
        optionalAppointment.ifPresent(entityPosition -> {
            entityPosition.delete();
            repository.save(entityPosition);
        });
    }

    private void validateAggregations(final AppointmentEntity entity) {
        if (StringUtils.isNotEmpty(entity.getDoctorId())) {
            doctorService.findById(entity.getDoctorId());
        }
        if (StringUtils.isNotEmpty(entity.getPatientId())) {
            patientService.findById(entity.getPatientId());
        }
    }

}
