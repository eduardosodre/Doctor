package br.com.project.domain.patientphone;

import br.com.project.domain.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientPhoneService {

    private static final String PATIENTPHONE_NOT_FOUND = "PatientPhone not found";
    private final PatientPhoneRepository repository;
 

    public PatientPhoneEntity findById(final String id) {
        return repository.findById(id).orElseThrow(() -> NotFoundException.create(PATIENTPHONE_NOT_FOUND));
    }

    public List<PatientPhoneEntity> findByIds(final List<String> ids) {
        return repository.findAllById(ids);
    }

    public void delete(final String id) {
        final var optionalPatientPhone = repository.findById(id);
        optionalPatientPhone.ifPresent(repository::delete);
    }
}
