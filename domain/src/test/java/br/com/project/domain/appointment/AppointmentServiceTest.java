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

        final var doctorId = DoctorEntity.create("string crmYkx", "string name07sZSQ", "string specialtycSg");
        when(doctorService.findById(any())).thenReturn(doctorId);

        final var patientId = PatientEntity.create("string nameXeEcK7", LocalDate.now(), "string gender00krw");
        when(patientService.findById(any())).thenReturn(patientId);

        final var newAppointmentEntity = AppointmentEntity.create("b3996fdd-5d63-4f86-bfb5-921d7a5092ca", "64b247ef-af4c-47ce-ba75-e308b046e928",
            LocalDateTime.now(), "string status5B9QI");

        when(repository.save(any())).thenReturn(newAppointmentEntity);

        service.save(newAppointmentEntity);

        verify(doctorService, times(1)).findById(newAppointmentEntity.getDoctorId());
        verify(patientService, times(1)).findById(newAppointmentEntity.getPatientId());
        verify(repository, times(1)).save(newAppointmentEntity);
    }

    @Test
    public void shouldUpdateAppointmentEntity() {
        final var appointmentEntityFromBd = AppointmentEntity.create("dccb34e7-9527-4666-8c37-5c4cd4fbab41", "d5da6aa8-9db9-41d0-a8e9-0ff6c305b17d",
            LocalDateTime.now(), "string statusc2Ewy");
        when(repository.findById(any())).thenReturn(Optional.of(appointmentEntityFromBd));

        final var doctorId = DoctorEntity.create("string crmgPw", "string nameal6vkf", "string specialtyjxm");
        when(doctorService.findById(any())).thenReturn(doctorId);

        final var patientId = PatientEntity.create("string nameGQTuHW", LocalDate.now(), "string gendertdnoJ");
        when(patientService.findById(any())).thenReturn(patientId);

        final var appointmentEntity = AppointmentEntity.with(appointmentEntityFromBd.getId(), "9c314611-d5db-4bbb-b55a-40a20bc04296",
            "c2c15672-77ae-4337-be2f-809ecfca2fe2", LocalDateTime.now(), "string statusrHaYk", appointmentEntityFromBd.getCreatedAt(),
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

        final var appointmentEntity = AppointmentEntity.create("84e4d784-acd1-4f18-8d98-7da53ae368a7", "371b9df5-8f9d-4903-b3c1-05badd5608c9",
            LocalDateTime.now(), "string statuschtrs");
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
        final var appointmentEntityFromBd = AppointmentEntity.create("a25e42f0-cd70-4bdf-b9f0-1ad8ccf6da63", "758b3ae0-1938-4d0a-8485-4042e26eca17",
            LocalDateTime.now(), "string status3BX60");
        when(repository.findById(any())).thenReturn(Optional.of(appointmentEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), appointmentEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var appointmentEntityFromBd = AppointmentEntity.create("61f9f2e1-c1b6-4ca9-ac85-6b4ec2090280", "84d4eaea-4415-4493-b723-8ba43166c0f7",
            LocalDateTime.now(), "string statusfjbuk");
        when(repository.findAllById(any())).thenReturn(List.of(appointmentEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var appointmentEntityFromBd = AppointmentEntity.create("bbe1e9c6-0ef2-4d8b-98eb-803f6a758036", "25186970-4dfc-4e03-aabc-948d9c3d7020",
            LocalDateTime.now(), "string statusRroaJ");
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

        final var appointmentEntityFromBd = AppointmentEntity.create("ec2e7b75-dadc-4c59-a7e0-88d449efb047", "69c3656c-5b58-4ffe-85e9-cbaf479273ef",
            LocalDateTime.now(), "string statusJg909");
        final var expectedPage = new PageImpl<>(List.of(appointmentEntityFromBd), page, 1);

        when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        final var resultList = service.findAll(filter, page);

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.getContent().size());
        verify(repository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }
}
