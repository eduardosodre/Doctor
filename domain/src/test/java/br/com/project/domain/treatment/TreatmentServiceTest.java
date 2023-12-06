package br.com.project.domain.treatment;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.project.domain.appointment.AppointmentEntity;
import br.com.project.domain.appointment.AppointmentService;
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
public class TreatmentServiceTest {

    @Mock
    private TreatmentRepository repository;
    @Mock
    private DoctorService doctorService;
    @Mock
    private PatientService patientService;
    @Mock
    private AppointmentService appointmentService;
    @InjectMocks
    private TreatmentService service;

    @Test
    public void shouldSaveANewTreatmentEntity() {

        final var doctorId = DoctorEntity.create("string crm9CZ", "string nameBU6Tpt", "string specialtyxk6");
        when(doctorService.findById(any())).thenReturn(doctorId);

        final var patientId = PatientEntity.create("string namem3bGEV", LocalDate.now(), "string genderVg2Sm");
        when(patientService.findById(any())).thenReturn(patientId);

        final var appointmentId = AppointmentEntity.create("536d4489-3681-4a3f-9d58-dcafa07fc173", "188042c2-efcb-42bd-9dc2-d38a3276f7aa",
            LocalDateTime.now(), "string status4jSKJ");
        when(appointmentService.findById(any())).thenReturn(appointmentId);

        final var newTreatmentEntity = TreatmentEntity.create("df825b1a-2e67-482f-b905-c1c143a403e2", "425f9812-e016-4c23-8906-09e3a8874faf",
            "9bddaf60-50c4-4858-b44a-61a35cea3dfe", "string diagnosisP47tC", "string prescriptionZJC4M", "string notesEKEE5", "string outcomehamE5");

        when(repository.save(any())).thenReturn(newTreatmentEntity);

        service.save(newTreatmentEntity);

        verify(doctorService, times(1)).findById(newTreatmentEntity.getDoctorId());
        verify(patientService, times(1)).findById(newTreatmentEntity.getPatientId());
        verify(appointmentService, times(1)).findById(newTreatmentEntity.getAppointmentId());
        verify(repository, times(1)).save(newTreatmentEntity);
    }

    @Test
    public void shouldUpdateTreatmentEntity() {
        final var treatmentEntityFromBd = TreatmentEntity.create("5bcb1135-68ae-44dd-9a89-ffb5a9fc6bb1", "e5200690-fafc-40e9-bca6-9ce23a5ebddf",
            "92e7b1a4-9da1-4b90-9bca-8795cc43da8a", "string diagnosisTJmvT", "string prescription437Fu", "string notesTQXtA", "string outcomeYsKW5");
        when(repository.findById(any())).thenReturn(Optional.of(treatmentEntityFromBd));

        final var doctorId = DoctorEntity.create("string crmD56", "string nameu64zCO", "string specialty5CF");
        when(doctorService.findById(any())).thenReturn(doctorId);

        final var patientId = PatientEntity.create("string namezv3rtH", LocalDate.now(), "string genderyxuwn");
        when(patientService.findById(any())).thenReturn(patientId);

        final var appointmentId = AppointmentEntity.create("d10c4e80-24f7-47c2-b977-c57aa71031cd", "5aac2d7f-b5de-43b2-978a-687d170d8c9c",
            LocalDateTime.now(), "string statusetAHb");
        when(appointmentService.findById(any())).thenReturn(appointmentId);

        final var treatmentEntity = TreatmentEntity.with(treatmentEntityFromBd.getId(), "021ef436-b56c-47b3-a11c-432febbedcc9",
            "2d5bbabd-f74a-49a9-9b02-45ee0eb7778f", "f9771df0-5a45-469a-800d-6b2bbe96c1dc", "string diagnosisaiMV3", "string prescriptionq9fsm",
            "string notes86eqi", "string outcomeCT2WN", treatmentEntityFromBd.getCreatedAt(), treatmentEntityFromBd.getUpdatedAt(),
            treatmentEntityFromBd.getDeletedAt());
        when(repository.save(any())).thenReturn(treatmentEntity);

        service.update(treatmentEntity.getId(), treatmentEntity);

        verify(doctorService, times(1)).findById(treatmentEntity.getDoctorId());
        verify(patientService, times(1)).findById(treatmentEntity.getPatientId());
        verify(appointmentService, times(1)).findById(treatmentEntity.getAppointmentId());
        verify(repository, times(1)).save(treatmentEntityFromBd);
        verify(repository, times(1)).findById(treatmentEntityFromBd.getId());
    }

    @Test
    public void shouldNotUpdateTreatmentEntityWhenTreatmentEntityNotExists() {
        when(repository.findById(any())).thenReturn(Optional.empty());

        final var treatmentEntity = TreatmentEntity.create("ba6b738d-c3d4-452f-89ad-f7f2a43e1c20", "d1d425ef-b32b-4ee4-bab3-2b866b824be5",
            "80ff200e-fa36-46ab-a61a-257a9309a300", "string diagnosiskgM5v", "string prescription4PxrF", "string notesmW99h", "string outcome80Qmk");
        final var expectedException = Assertions.assertThrows(NotFoundException.class,
            () -> service.update(treatmentEntity.getId(), treatmentEntity));

        Assertions.assertEquals("Treatment not found", expectedException.getMessage());

        verify(doctorService, never()).findById(treatmentEntity.getDoctorId());
        verify(patientService, never()).findById(treatmentEntity.getPatientId());
        verify(appointmentService, never()).findById(treatmentEntity.getAppointmentId());
        verify(repository, never()).save(any());
        verify(repository, times(1)).findById(treatmentEntity.getId());
    }

    @Test
    public void shouldGetById() {
        final var treatmentEntityFromBd = TreatmentEntity.create("523ef386-c397-490b-b899-da024974c859", "6f960cda-c72c-47cf-9500-073e74204618",
            "7ab0ba31-51df-4f8d-ba94-e020f73507a5", "string diagnosisDdfV5", "string prescriptionP2SHV", "string notescksVs", "string outcomeJ49Lp");
        when(repository.findById(any())).thenReturn(Optional.of(treatmentEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), treatmentEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var treatmentEntityFromBd = TreatmentEntity.create("9a95dbe7-22c2-4606-9b91-24ee0f40622a", "9498737a-e886-427c-9afd-b245db5ab74a",
            "b36cd7f6-b638-4bd7-bdd2-3aef319de61e", "string diagnosis5KM0x", "string prescriptionWNfHF", "string notes339Na", "string outcomeDMB8K");
        when(repository.findAllById(any())).thenReturn(List.of(treatmentEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var treatmentEntityFromBd = TreatmentEntity.create("a7317f67-ee03-461f-b980-e95714cd232f", "996dcf0c-5588-421e-9966-8afd84d8bac9",
            "a3602ba0-4bce-4260-bc3a-c42bf4afc386", "string diagnosisB0PUQ", "string prescriptioncQ4ae", "string notesXFA0U", "string outcomemMgB1");
        when(repository.findById(any())).thenReturn(Optional.of(treatmentEntityFromBd));
        when(repository.save(any())).thenReturn(treatmentEntityFromBd);

        service.delete("id");

        verify(repository, times(1)).save(any());
        verify(repository, times(1)).findById(any());
    }


    @Test
    public void shouldFindAll() {
        final var page = Pageable.unpaged();
        final var filter = new TreatmentFilter();

        final var treatmentEntityFromBd = TreatmentEntity.create("bed3168e-4bcc-4539-ae1e-efd3163c7bf7", "ff38b7d8-b1c7-458b-a503-f779c163a27f",
            "1901bc70-858d-410f-854f-4282bdb22d85", "string diagnosiswqKd9", "string prescriptionApBk0", "string notesa2x0R", "string outcome9jtHC");
        final var expectedPage = new PageImpl<>(List.of(treatmentEntityFromBd), page, 1);

        when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        final var resultList = service.findAll(filter, page);

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.getContent().size());
        verify(repository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }
}
