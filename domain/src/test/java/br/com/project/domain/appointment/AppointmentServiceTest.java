package br.com.project.domain.appointment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.project.domain.doctor.DoctorEntity;
import br.com.project.domain.doctor.DoctorService;
import br.com.project.domain.exceptions.NotFoundException;
import br.com.project.domain.patient.PatientEntity;
import br.com.project.domain.patient.PatientService;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository repository;
    @Mock
    private DoctorService doctorService;
    @Mock
    private PatientService patientService;
    @InjectMocks
    private AppointmentService service;

    @Test
    public void shouldSaveANewAppointmentEntity() {

        final var doctorId = DoctorEntity.create("string crm6wI", "string namevFwLMG", "string specialtyaQY");
        when(doctorService.findById(any())).thenReturn(doctorId);

        final var patientId = PatientEntity.create("string namej0cHsT", LocalDate.now(), "string gendergmgQV");
        when(patientService.findById(any())).thenReturn(patientId);

        final var newAppointmentEntity = AppointmentEntity.create("29970b98-bf99-4974-816f-20e49ad42865", "82418514-5742-4f3f-8830-0ac81ef7f785",
            LocalDateTime.now(), "string statuskrU1i");

        when(repository.save(any())).thenReturn(newAppointmentEntity);

        service.save(newAppointmentEntity);

        verify(doctorService, times(1)).findById(newAppointmentEntity.getDoctorId());
        verify(patientService, times(1)).findById(newAppointmentEntity.getPatientId());
        verify(repository, times(1)).save(newAppointmentEntity);
    }

    @Test
    public void shouldUpdateAppointmentEntity() {
        final var appointmentEntityFromBd = AppointmentEntity.create("bdb5b1f0-2384-4e3e-b847-65ab13a4b7f4", "596bde6b-d258-4c75-ada0-269add3b6412",
            LocalDateTime.now(), "string statuslJAv0");
        when(repository.findById(any())).thenReturn(Optional.of(appointmentEntityFromBd));

        final var doctorId = DoctorEntity.create("string crmIRA", "string namee1cXEs", "string specialtyAc4");
        when(doctorService.findById(any())).thenReturn(doctorId);

        final var patientId = PatientEntity.create("string nameaFVCEJ", LocalDate.now(), "string gender5gBzf");
        when(patientService.findById(any())).thenReturn(patientId);

        final var appointmentEntity = AppointmentEntity.with(appointmentEntityFromBd.getId(), "74693ecd-fdf7-496e-9e72-437097d64d2d",
            "fc5abb9c-9127-4bb9-bbff-669e4e8ae751", LocalDateTime.now(), "string statusL8B6k", appointmentEntityFromBd.getCreatedAt(),
            appointmentEntityFromBd.getUpdatedAt(), appointmentEntityFromBd.getDeletedAt());
        when(repository.save(any())).thenReturn(appointmentEntity);

        service.update(appointmentEntity.getId(), appointmentEntity);

        verify(doctorService, times(1)).findById(appointmentEntity.getDoctorId());
        verify(patientService, times(1)).findById(appointmentEntity.getPatientId());
        verify(repository, times(1)).save(appointmentEntityFromBd);
        verify(repository, times(1)).findById(appointmentEntityFromBd.getId());
    }

    @Test
    public void shouldNotUpdateAppointmentEntityWhenAppointmentEntityNotExists() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        final var appointmentEntity = AppointmentEntity.create("02ca3a0a-d573-4fe5-a328-9804509dc746", "4deae1f2-2f02-4b91-a146-fcb8546f3313",
            LocalDateTime.now(), "string statusujr0o");
        final var expectedException = Assertions.assertThrows(NotFoundException.class,
            () -> service.update(appointmentEntity.getId(), appointmentEntity));

        Assertions.assertEquals("Appointment not found", expectedException.getMessage());

        verify(doctorService, never()).findById(appointmentEntity.getDoctorId());
        verify(patientService, never()).findById(appointmentEntity.getPatientId());
        verify(repository, never()).save(any());
        verify(repository, times(1)).findById(appointmentEntity.getId());
    }

    @Test
    public void shouldGetById() {
        final var appointmentEntityFromBd = AppointmentEntity.create("9ba6aea0-7cce-475e-bcec-008de3376427", "8fa68a61-a6f9-4200-9931-f59bdfb74d64",
            LocalDateTime.now(), "string statusKIjwg");
        when(repository.findById(any())).thenReturn(Optional.of(appointmentEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), appointmentEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var appointmentEntityFromBd = AppointmentEntity.create("d56d1b99-2860-4ccd-a662-991c0e012016", "2d91bf86-a98c-4966-ba54-38dea29e1b9f",
            LocalDateTime.now(), "string statusz00q9");
        when(repository.findAllById(any())).thenReturn(List.of(appointmentEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var appointmentEntityFromBd = AppointmentEntity.create("79092da8-5189-47bc-980d-1bb91dee8b27", "4ef0402f-f60d-43f7-8f56-e63cac4d2c7e",
            LocalDateTime.now(), "string statusBTja1");
        when(repository.findById(any())).thenReturn(Optional.of(appointmentEntityFromBd));
        when(repository.save(any())).thenReturn(appointmentEntityFromBd);

        service.delete("id");

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).findById(any());
    }


    @Test
    public void shouldFindAll() {
        final var page = Pageable.unpaged();
        final var filter = new AppointmentFilter();

        final var appointmentEntityFromBd = AppointmentEntity.create("573138d6-51ae-4f72-9a6f-8e7cb6601779", "8bbfbdab-57d8-448f-913b-aa92fc2d25ed",
            LocalDateTime.now(), "string statusiV8QG");
        final var expectedPage = new PageImpl<>(List.of(appointmentEntityFromBd), page, 1);

        when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        final var resultList = service.findAll(filter, page);

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.getContent().size());
        verify(repository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }
}
