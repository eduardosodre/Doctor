package br.com.project.domain.patientaddress;

import br.com.project.domain.exceptions.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientAddressService {

    private static final String PATIENTADDRESS_NOT_FOUND = "PatientAddress not found";
    private final PatientAddressRepository repository;
 

    public PatientAddressEntity findById(final String id) {
        return repository.findById(id).orElseThrow(() -> NotFoundException.create(PATIENTADDRESS_NOT_FOUND));
    }

    public List<PatientAddressEntity> findByIds(final List<String> ids) {
        return repository.findAllById(ids);
    }

    public void delete(final String id) {
        final var optionalPatientAddress = repository.findById(id);
        optionalPatientAddress.ifPresent(repository::delete);
    }
}
