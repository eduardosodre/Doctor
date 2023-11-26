package br.com.project.domain.patientemail;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.project.domain.patient.PatientEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PatientEmailServiceTest {
 
    @Mock
    private PatientEmailRepository repository;
    @InjectMocks
    private PatientEmailService service;


    @Test
    public void shouldGetById() {
        final var patientEmailEntityFromBd = PatientEmailEntity.create("string emailP7Dk6",
            PatientEntity.create("string name4i77EE", LocalDate.now(), "string genderRnCaK"));
        when(repository.findById(any())).thenReturn(Optional.of(patientEmailEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), patientEmailEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var patientEmailEntityFromBd = PatientEmailEntity.create("string emailEyOKX",
            PatientEntity.create("string nameOrqeIe", LocalDate.now(), "string gendereJFLf"));
        when(repository.findAllById(any())).thenReturn(List.of(patientEmailEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var patientEmailEntityFromBd = PatientEmailEntity.create("string emailP1Seb",
            PatientEntity.create("string name5AJQU1", LocalDate.now(), "string genderz3BTv"));
        when(repository.findById(any())).thenReturn(Optional.of(patientEmailEntityFromBd));
        doNothing().when(repository).delete(any(PatientEmailEntity.class));

        service.delete("id");

        verify(repository, times(1)).delete(any(PatientEmailEntity.class));
        verify(repository, times(1)).findById(any());
    }

}
