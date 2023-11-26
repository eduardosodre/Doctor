package br.com.project.domain.doctor;

import br.com.project.domain.exceptions.DomainException;
import br.com.project.domain.exceptions.NotFoundException;
import br.com.project.domain.validation.Error;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private static final String DOCTOR_NOT_FOUND = "Doctor not found";
    private final DoctorRepository repository;

    public DoctorEntity save(final DoctorEntity entity) {
        validateUniqueAttributes(entity);
        final var savedEntity = repository.save(entity);
        return savedEntity;
    }

    public DoctorEntity update(final String id, final DoctorEntity entity) {
        final var entityFromBd = findById(id);
        entityFromBd.update(
            entity.getCrm(),
            entity.getName(),
            entity.getSpecialty());
        validateUniqueAttributes(entityFromBd);
        final var savedEntity = repository.save(entityFromBd);
        return savedEntity;
    }

    public DoctorEntity findById(final String id) {
        return repository.findById(id).orElseThrow(() -> NotFoundException.create(DOCTOR_NOT_FOUND));
    }

    public List<DoctorEntity> findByIds(final List<String> ids) {
        return repository.findAllById(ids);
    }

    public Page<DoctorEntity> findAll(final DoctorFilter filter, final Pageable pageable) {
        return repository.findAll(DoctorSpecifications.filter(filter), pageable);
    }

    public void delete(final String id) {
        final var optionalDoctor = repository.findById(id);
        optionalDoctor.ifPresent(entityPosition -> {
            entityPosition.delete();
            repository.save(entityPosition);
        });
    }

    private void validateUniqueAttributes(final DoctorEntity entity) {
        final var crmListId = repository.findByCrm(entity.getCrm())
            .stream()
            .map(DoctorEntity::getId)
            .collect(Collectors.toList());
        crmListId.remove(entity.getId());
        if (!crmListId.isEmpty()) {
            throw DomainException.with(new Error("There is already a record with this crm"));
        }

    }

}
