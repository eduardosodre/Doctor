package br.com.project.domain.patientaddress;

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
public class PatientAddressServiceTest {

    @Mock
    private PatientAddressRepository repository;
    @InjectMocks
    private PatientAddressService service;


    @Test
    public void shouldGetById() {
        final var patientAddressEntityFromBd = PatientAddressEntity.create("string street3IY43", "string numberJH8ob", "string neighborhoodg29rJ",
            "string cityNYROv", "string statedlHeN", "string countryvefag",
            PatientEntity.create("string nameeZVFgc", LocalDate.now(), "string gendereJYs9"));
        when(repository.findById(any())).thenReturn(Optional.of(patientAddressEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), patientAddressEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var patientAddressEntityFromBd = PatientAddressEntity.create("string streetM3pyr", "string numberAvaH5", "string neighborhoodJcjWB",
            "string cityyrKhg", "string stateuY4t5", "string countryeEHXt",
            PatientEntity.create("string namez1YqgR", LocalDate.now(), "string genderUlmU8"));
        when(repository.findAllById(any())).thenReturn(List.of(patientAddressEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var patientAddressEntityFromBd = PatientAddressEntity.create("string street6lI6U", "string numberBX8Ws", "string neighborhoodYGq0t",
            "string cityZvvqv", "string state5zVjO", "string countryqRsJ6",
            PatientEntity.create("string name8SP0Gd", LocalDate.now(), "string gendervl5Hu"));
        when(repository.findById(any())).thenReturn(Optional.of(patientAddressEntityFromBd));
        doNothing().when(repository).delete(any(PatientAddressEntity.class));

        service.delete("id");

        verify(repository, times(1)).delete(any(PatientAddressEntity.class));
        verify(repository, times(1)).findById(any());
    }

}
