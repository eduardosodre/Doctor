package br.com.project.domain.patientphone;

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
public class PatientPhoneServiceTest {
 
    @Mock
    private PatientPhoneRepository repository;
    @InjectMocks
    private PatientPhoneService service;


    @Test
    public void shouldGetById() {
        final var patientPhoneEntityFromBd = PatientPhoneEntity.create("string phoneAP308",
            PatientEntity.create("string nameo9K2rm", LocalDate.now(), "string gender2UkLp"));
        when(repository.findById(any())).thenReturn(Optional.of(patientPhoneEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), patientPhoneEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var patientPhoneEntityFromBd = PatientPhoneEntity.create("string phoneDeYc9",
            PatientEntity.create("string nameK6SiTx", LocalDate.now(), "string genderF1nZC"));
        when(repository.findAllById(any())).thenReturn(List.of(patientPhoneEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var patientPhoneEntityFromBd = PatientPhoneEntity.create("string phoneDhZL3",
            PatientEntity.create("string namepWpoPI", LocalDate.now(), "string gendermSGCw"));
        when(repository.findById(any())).thenReturn(Optional.of(patientPhoneEntityFromBd));
        doNothing().when(repository).delete(any(PatientPhoneEntity.class));

        service.delete("id");

        verify(repository, times(1)).delete(any(PatientPhoneEntity.class));
        verify(repository, times(1)).findById(any());
    }

}
