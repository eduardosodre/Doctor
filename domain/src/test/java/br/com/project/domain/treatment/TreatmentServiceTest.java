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

        final var doctorId = DoctorEntity.create("string crmmml", "string namez8jWHf", "string specialtyMX9");
        when(doctorService.findById(any())).thenReturn(doctorId);

        final var patientId = PatientEntity.create("string nameVozIoe", LocalDate.now(), "string genderldOZ6");
        when(patientService.findById(any())).thenReturn(patientId);

        final var appointmentId = AppointmentEntity.create("55bbc73f-f6e9-4fe4-89c9-4217a38c32b6", "d3f32866-f559-4348-a844-6d46d8c354f7",
            LocalDateTime.now(), "string statusMai2L");
        when(appointmentService.findById(any())).thenReturn(appointmentId);

        final var newTreatmentEntity = TreatmentEntity.create("f9774759-40f4-4830-a85a-00a31f0e6dc6", "f73d1938-ebf9-496d-a016-7313ca6a97cd",
            "422e97de-62c6-4618-9b33-cb078ebb8298", "string diagnosisNNGer", "string prescriptionc1Dp4", "string notesI5GBN", "string outcomeUSDlp");

        when(repository.save(any())).thenReturn(newTreatmentEntity);

        service.save(newTreatmentEntity);

        verify(doctorService, times(1)).findById(newTreatmentEntity.getDoctorId());
        verify(patientService, times(1)).findById(newTreatmentEntity.getPatientId());
        verify(appointmentService, times(1)).findById(newTreatmentEntity.getAppointmentId());
        verify(repository, times(1)).save(newTreatmentEntity);
    }

    @Test
    public void shouldUpdateTreatmentEntity() {
        final var treatmentEntityFromBd = TreatmentEntity.create("5b9e7182-e2fe-425a-99b2-b96b8d5d1b99", "01710446-3124-4230-8cc7-a7abef3ceed7",
            "d13dbac9-6f79-486d-b7be-0792c5b58867", "string diagnosishBglk", "string prescriptionWAmtJ", "string notesnFgiL", "string outcomeGemHw");
        when(repository.findById(any())).thenReturn(Optional.of(treatmentEntityFromBd));

        final var doctorId = DoctorEntity.create("string crm7yw", "string nameIAAmTv", "string specialtybKn");
        when(doctorService.findById(any())).thenReturn(doctorId);

        final var patientId = PatientEntity.create("string namecszPPH", LocalDate.now(), "string genderbUDUe");
        when(patientService.findById(any())).thenReturn(patientId);

        final var appointmentId = AppointmentEntity.create("f00dab80-9e3e-4513-a78d-4a1eecb3d8ec", "04e4e6b4-6edb-4f91-af0a-d66f12e84310",
            LocalDateTime.now(), "string statusehG6y");
        when(appointmentService.findById(any())).thenReturn(appointmentId);

        final var treatmentEntity = TreatmentEntity.with(treatmentEntityFromBd.getId(), "fba4fb17-1279-4787-8a3b-860e651124a3",
            "599b7814-a7fa-458f-8ab8-a17aef6d64ae", "a0913b14-7dec-4b8c-a01e-f9f2403cdb0d", "string diagnosisi1AVc", "string prescription4IDKx",
            "string notesX8bqa", "string outcomeDa4we", treatmentEntityFromBd.getCreatedAt(), treatmentEntityFromBd.getUpdatedAt(),
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

        final var treatmentEntity = TreatmentEntity.create("957a0724-e9b3-4f51-acb0-08b8a0f87504", "44f10609-f207-453b-ae14-c62edad9891b",
            "52708d44-dae6-4a33-b969-e7e9c4442b78", "string diagnosisWn4Iz", "string prescriptionDQ4Lk", "string notes5Z425", "string outcome03cjw");
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
        final var treatmentEntityFromBd = TreatmentEntity.create("19114745-5a9a-40a5-91a9-a73551240f86", "006a8031-197f-4f7b-b59c-8cddc6b329a7",
            "1e4483d9-4c62-4895-a91b-1fec82aee9a8", "string diagnosisn5Nka", "string prescriptionTOxVE", "string notesIEqee", "string outcomedxAOh");
        when(repository.findById(any())).thenReturn(Optional.of(treatmentEntityFromBd));

        final var result = service.findById("id");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), treatmentEntityFromBd.getId());
        verify(repository, times(1)).findById(any());
    }

    @Test
    public void shouldGetByIds() {
        final var treatmentEntityFromBd = TreatmentEntity.create("27441253-abf5-45cb-a73b-7d06c625d6a5", "e9271862-daa4-4122-af1f-a9118d9e084d",
            "75f91ca4-8015-4d15-aac9-9c3de46663be", "string diagnosis1nw99", "string prescriptionzD3bE", "string notes0NGbO", "string outcomeEKLou");
        when(repository.findAllById(any())).thenReturn(List.of(treatmentEntityFromBd));

        final var resultList = service.findByIds(List.of("id"));

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.size());
        verify(repository, times(1)).findAllById(any());
    }

    @Test
    public void shouldDelete() {
        final var treatmentEntityFromBd = TreatmentEntity.create("f072df0d-f93a-4a71-b7e9-08433ed742ff", "d9a9580c-61bf-41e7-883e-9e5f89d71a21",
            "8c0b6744-e8ec-468c-bc03-a98160fa3219", "string diagnosisZxJz3", "string prescription2ax01", "string notesrb8nB", "string outcomesdPt0");
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

        final var treatmentEntityFromBd = TreatmentEntity.create("2d4d2101-28a6-46ff-9154-abd7885f5707", "cb4546cb-611d-4031-997c-06b22a300b81",
            "7dc4cb68-def9-4e92-8404-8a2c0fefa40a", "string diagnosiswOFdh", "string prescriptionBflSJ", "string notesNvb4U", "string outcomeP7neu");
        final var expectedPage = new PageImpl<>(List.of(treatmentEntityFromBd), page, 1);

        when(repository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(expectedPage);
        final var resultList = service.findAll(filter, page);

        Assertions.assertNotNull(resultList);
        Assertions.assertEquals(1, resultList.getContent().size());
        verify(repository, times(1)).findAll(any(Specification.class), any(Pageable.class));
    }
}
