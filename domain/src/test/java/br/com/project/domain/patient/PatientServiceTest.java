package br.com.project.domain.patient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.project.domain.exceptions.NotFoundException;
import br.com.project.domain.patientaddress.PatientAddressService;
import br.com.project.domain.patientemail.PatientEmailService;
import br.com.project.domain.patientphone.PatientPhoneService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
 
    @Mock
    private PatientRepository repository;
    @Mock
    private PatientPhoneService phonesService;
    @Mock
    private PatientAddressService addressesService;
    @Mock
    private PatientEmailService emailsService;
    @InjectMocks
    private PatientService service;

    @Test
    public void shouldSaveANewPatientEntity() {

        final var newPatientEntity = PatientEntity.create("string nameNa4KyH", LocalDate.now(), "string genderu4n37");

        when(repository.save(any())).thenReturn(newPatientEntity);

        service.save(newPatientEntity);

        verify(repository, times(1)).save(newPatientEntity);
    }

    @Test
    public void shouldUpdatePatientEntity() {
        final var patientEntityFromBd = PatientEntity.create("string namerBGxcz", LocalDate.now(), "string gender81wFq");
        when(repository.findById(any())).thenReturn(Optional.of(patientEntityFromBd));

        final var patientEntity = PatientEntity.with(patientEntityFromBd.getId(), "string nameQEpFeD", LocalDate.now(), "string gender9njqs",
            patientEntityFromBd.getCreatedAt(), patientEntityFromBd.getUpdatedAt(), patientEntityFromBd.getDeletedAt());
        when(repository.save(any())).thenReturn(patientEntity);

        service.update(patientEntity.getId(), patientEntity);

        verify(repository, times(1)).save(patientEntityFromBd);
        verify(repository, times(1)).findById(patientEntityFromBd.getId());
    }

    @Test
    public void shouldNotUpdatePatientEntityWhenPatientEntityNotExists() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        final var patientEntity = PatientEntity.create("string nameG8WiO5", LocalDate.now(), "string gender9sSPS");
        final var expectedException = Assertions.assertThrows(NotFoundException.class,
            () -> service.update(patientEntity.getId(), patientEntity));

        Assertions.assertEquals("Patient not found", expectedException.getMessage());

        verify(repository, never()).save(any());
        verify(repository, times(1)).findById(patientEntity.getId());
    }

    @Test
    public void shouldGetById() {
        final var patientEntityFromBd = PatientEntity.create("string namelfAmpm", LocalDate.now(), "string genderjfjxP");
        when(repository.findById(any())).thenReturn(Optional.of(patientEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), patientEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var patientEntityFromBd = PatientEntity.create("string nametCL7Cm", LocalDate.now(), "string genderIJowE");
        when(repository.findAllById(any())).thenReturn(List.of(patientEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var patientEntityFromBd = PatientEntity.create("string nameNH4G2y", LocalDate.now(), "string genderZreiJ");
        when(repository.findById(any())).thenReturn(Optional.of(patientEntityFromBd));
        when(repository.save(any())).thenReturn(patientEntityFromBd);

        service.delete("id");

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldDeletePhones() {
        final var patientEntityFromBd = PatientEntity.create("string nameFF3gGw", LocalDate.now(), "string genderdI5h2");
        when(repository.findById(any())).thenReturn(Optional.of(patientEntityFromBd));
        doNothing().when(phonesService).delete(any());

        service.deletePatientPhone("id", "id");

        verify(phonesService, times(1)).delete(any());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldDeleteAddresses() {
        final var patientEntityFromBd = PatientEntity.create("string nameFF3gGw", LocalDate.now(), "string genderdI5h2");
        when(repository.findById(any())).thenReturn(Optional.of(patientEntityFromBd));
        doNothing().when(addressesService).delete(any());

        service.deletePatientAddress("id", "id");

        verify(addressesService, times(1)).delete(any());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldDeleteEmails() {
        final var patientEntityFromBd = PatientEntity.create("string nameFF3gGw", LocalDate.now(), "string genderdI5h2");
        when(repository.findById(any())).thenReturn(Optional.of(patientEntityFromBd));
        doNothing().when(emailsService).delete(any());

        service.deletePatientEmail("id", "id");

        verify(emailsService, times(1)).delete(any());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldFindAll() {
        final var page = Pageable.unpaged();
        final var filter = new PatientFilter();

        final var patientEntityFromBd = PatientEntity.create("string nameYggTkS", LocalDate.now(), "string genderZzWGR");
        final var expectedPage = new PageImpl<>(List.of(patientEntityFromBd), page, 1);

        when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        final var resultList = service.findAll(filter, page);

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.getContent().size());
        verify(repository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }
}
