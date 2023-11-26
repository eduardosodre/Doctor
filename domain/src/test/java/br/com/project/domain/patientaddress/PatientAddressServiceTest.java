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
        final var patientAddressEntityFromBd = PatientAddressEntity.create("string streetA5d2Y", "string numberjthi1", "string neighborhoodKSshu",
            "string city2uaJS", "string stateewI11", "string country7Peux",
            PatientEntity.create("string nameyaSWMG", LocalDate.now(), "string genderLxfhd"));
        when(repository.findById(any())).thenReturn(Optional.of(patientAddressEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), patientAddressEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var patientAddressEntityFromBd = PatientAddressEntity.create("string streetooope", "string numberuBfHz", "string neighborhoodP7jFS",
            "string cityKNcX8", "string statePxA4N", "string countryhl5zR",
            PatientEntity.create("string nameH6iuMn", LocalDate.now(), "string genderJb0ej"));
        when(repository.findAllById(any())).thenReturn(List.of(patientAddressEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var patientAddressEntityFromBd = PatientAddressEntity.create("string street9yrX6", "string numberiflSR", "string neighborhood6xXmJ",
            "string cityMyHPR", "string stateLqS7f", "string countryFZnch",
            PatientEntity.create("string name5YljAr", LocalDate.now(), "string genderjq1bS"));
        when(repository.findById(any())).thenReturn(Optional.of(patientAddressEntityFromBd));
        doNothing().when(repository).delete(any(PatientAddressEntity.class));

        service.delete("id");

        verify(repository, times(1)).delete(any(PatientAddressEntity.class));
        verify(repository, times(1)).findById(any());
    }

}
