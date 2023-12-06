package br.com.project.domain.doctor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.project.domain.exceptions.DomainException;
import br.com.project.domain.exceptions.NotFoundException;
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
public class DoctorServiceTest {

    @Mock
    private DoctorRepository repository;
    @InjectMocks
    private DoctorService service;

    @Test
    public void shouldSaveANewDoctorEntity() {
        when(repository.findByCrm(any())).thenReturn(List.of());

        final var newDoctorEntity = DoctorEntity.create("string crmxCV", "string nameRpR1XU", "string specialty4GB");

        when(repository.save(any())).thenReturn(newDoctorEntity);

        service.save(newDoctorEntity);

        verify(repository, times(1)).findByCrm(newDoctorEntity.getCrm());
        verify(repository, times(1)).save(newDoctorEntity);
    }

    @Test
    public void shouldNotSaveANewDoctorEntityWhenCrmIsNotUnique() {
        final var doctorEntityFromBd = DoctorEntity.create("string crmj4O", "string nameQPVpv2", "string specialtyz56");
        when(repository.findByCrm(any())).thenReturn(List.of(doctorEntityFromBd));

        final var newDoctorEntity = DoctorEntity.create("string crmveP", "string name2oV7Gt", "string specialtyWTb");
        final var expectedException = Assertions.assertThrows(DomainException.class,
            () -> service.save(newDoctorEntity));

        Assertions.assertEquals("There is already a record with this crm", expectedException.getMessage());
        verify(repository, times(1)).findByCrm(newDoctorEntity.getCrm());
        verify(repository, never()).save(any());
    }

    @Test
    public void shouldUpdateDoctorEntity() {
        final var doctorEntityFromBd = DoctorEntity.create("string crmT9f", "string name8JnjJh", "string specialty2aX");
        when(repository.findById(any())).thenReturn(Optional.of(doctorEntityFromBd));

        when(repository.findByCrm(any())).thenReturn(List.of());

        final var doctorEntity = DoctorEntity.with(doctorEntityFromBd.getId(), "string crmzxo", "string nameGldAsq", "string specialty8bh",
            doctorEntityFromBd.getCreatedAt(), doctorEntityFromBd.getUpdatedAt(), doctorEntityFromBd.getDeletedAt());
        when(repository.save(any())).thenReturn(doctorEntity);

        service.update(doctorEntity.getId(), doctorEntity);

        verify(repository, times(1)).findByCrm(doctorEntity.getCrm());
        verify(repository, times(1)).save(doctorEntityFromBd);
        verify(repository, times(1)).findById(doctorEntityFromBd.getId());
    }

    @Test
    public void shouldNotUpdateDoctorEntityWhenDoctorEntityNotExists() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        final var doctorEntity = DoctorEntity.create("string crm1Vk", "string name3zKOLj", "string specialtyq7c");
        final var expectedException = Assertions.assertThrows(NotFoundException.class,
            () -> service.update(doctorEntity.getId(), doctorEntity));

        Assertions.assertEquals("Doctor not found", expectedException.getMessage());

        verify(repository, never()).findByCrm(doctorEntity.getCrm());
        verify(repository, never()).save(any());
        verify(repository, times(1)).findById(doctorEntity.getId());
    }

    @Test
    public void shouldUpdateDoctorEntityWhenCrmIsNotUnique() {
        final var doctorEntityFromBd = DoctorEntity.create("string crmgsG", "string name6d5FXi", "string specialtyIQr");
        when(repository.findById(any())).thenReturn(Optional.of(doctorEntityFromBd));

        final var doctorEntityWithCrmFromBd = DoctorEntity.create("string crmdKA", "string nameFB9ExI", "string specialtyloY");
        when(repository.findByCrm(any())).thenReturn(List.of(doctorEntityWithCrmFromBd));

        final var doctorEntity = DoctorEntity.with(doctorEntityFromBd.getId(), "string crmpaW", "string namef7ecGI", "string specialtysTd",
            doctorEntityFromBd.getCreatedAt(), doctorEntityFromBd.getUpdatedAt(), doctorEntityFromBd.getDeletedAt());
        final var expectedException = Assertions.assertThrows(DomainException.class,
            () -> service.update(doctorEntity.getId(), doctorEntity));

        Assertions.assertEquals("There is already a record with this crm", expectedException.getMessage());

        verify(repository, times(1)).findByCrm(doctorEntity.getCrm());
        verify(repository, never()).save(doctorEntityFromBd);
        verify(repository, times(1)).findById(doctorEntityFromBd.getId());
    }

    @Test
    public void shouldGetById() {
        final var doctorEntityFromBd = DoctorEntity.create("string crm2HN", "string namev8J6Fm", "string specialtyR0M");
        when(repository.findById(any())).thenReturn(Optional.of(doctorEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), doctorEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var doctorEntityFromBd = DoctorEntity.create("string crmG5J", "string nameHTniLV", "string specialtyP71");
        when(repository.findAllById(any())).thenReturn(List.of(doctorEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var doctorEntityFromBd = DoctorEntity.create("string crmMXI", "string nameG4o5o3", "string specialtyvGI");
        when(repository.findById(any())).thenReturn(Optional.of(doctorEntityFromBd));
        when(repository.save(any())).thenReturn(doctorEntityFromBd);

        service.delete("id");

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).findById(any());
    }


    @Test
    public void shouldFindAll() {
        final var page = Pageable.unpaged();
        final var filter = new DoctorFilter();

        final var doctorEntityFromBd = DoctorEntity.create("string crm8XJ", "string namezzfTWr", "string specialtyTII");
        final var expectedPage = new PageImpl<>(List.of(doctorEntityFromBd), page, 1);

        when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        final var resultList = service.findAll(filter, page);

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.getContent().size());
        verify(repository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }
}
