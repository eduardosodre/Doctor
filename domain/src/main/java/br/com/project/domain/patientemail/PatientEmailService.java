package br.com.project.domain.patientemail;

import br.com.project.domain.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientEmailService {

    private static final String PATIENTEMAIL_NOT_FOUND = "PatientEmail not found";
    private final PatientEmailRepository repository;


    public PatientEmailEntity findById(final String id) {
        return repository.findById(id).orElseThrow(() -> NotFoundException.create(PATIENTEMAIL_NOT_FOUND));
    }

    public List<PatientEmailEntity> findByIds(final List<String> ids) {
        return repository.findAllById(ids);
    }

    public void delete(final String id) {
        final var optionalPatientEmail = repository.findById(id);
        optionalPatientEmail.ifPresent(repository::delete);
    }
}
