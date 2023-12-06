package br.com.project.api.v1.treatment;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.project.api.v1.ControllerTest;
import br.com.project.domain.exceptions.NotFoundException;
import br.com.project.domain.treatment.TreatmentEntity;
import br.com.project.domain.treatment.TreatmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ControllerTest(controllers = TreatmentController.class)
public class TreatmentControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private TreatmentService service;

    @Test
    void shouldFindAllTreatment() throws Exception {
        final var expectedId = "id";
        final var expectedDoctorId = "f07eaa2c-1220-4e64-8e25-cdcde83ec215";
        final var expectedPatientId = "1529c251-56b3-46a0-b22f-a26fe67f3afe";
        final var expectedAppointmentId = "c248e593-050c-4e32-b4c2-9827f26526de";
        final var expectedDiagnosis = "string diagnosisKjN3w";
        final var expectedPrescription = "string prescriptionApK5R";
        final var expectedNotes = "string noteswEH4j";
        final var expectedOutcome = "string outcomeO246K";

        final var actualTreatmentEntity = TreatmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);
        final var page = Pageable.unpaged();
        final var expectedPage = new PageImpl<>(List.of(actualTreatmentEntity), page, 1);
        when(service.findAll(any(), any())).thenReturn(expectedPage);

        final var request = get("/v1/treatments")
            .accept(MediaType.APPLICATION_JSON)
            .param("doctorId", expectedDoctorId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.content[0]doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.content[0]patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.content[0]appointmentId", equalTo(expectedAppointmentId)))
            .andExpect(jsonPath("$.content[0]diagnosis", equalTo(expectedDiagnosis)))
            .andExpect(jsonPath("$.content[0]prescription", equalTo(expectedPrescription)))
            .andExpect(jsonPath("$.content[0]notes", equalTo(expectedNotes)))
            .andExpect(jsonPath("$.content[0]outcome", equalTo(expectedOutcome)));
        verify(service).findAll(any(), any());
    }

    @Test
    void shouldGetTreatmentById() throws Exception {
        final var expectedId = "id";
        final var expectedDoctorId = "391bad0a-7f43-4389-b162-5cb74483c520";
        final var expectedPatientId = "b6848d9c-bfed-47b9-ae6b-6267a6b44635";
        final var expectedAppointmentId = "cc88b45e-d8d6-40e2-becd-c93bc97b57fc";
        final var expectedDiagnosis = "string diagnosiskALJG";
        final var expectedPrescription = "string prescriptionq0HYp";
        final var expectedNotes = "string notestX0pB";
        final var expectedOutcome = "string outcomeXCzMp";

        final var actualTreatmentEntity = TreatmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);
        when(service.findById(any())).thenReturn(actualTreatmentEntity);

        final var request = get("/v1/treatments/{id}", expectedId)
            .accept(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.appointmentId", equalTo(expectedAppointmentId)))
            .andExpect(jsonPath("$.diagnosis", equalTo(expectedDiagnosis)))
            .andExpect(jsonPath("$.prescription", equalTo(expectedPrescription)))
            .andExpect(jsonPath("$.notes", equalTo(expectedNotes)))
            .andExpect(jsonPath("$.outcome", equalTo(expectedOutcome)));
        verify(service).findById(expectedId);
    }

    @Test
    void shouldGetTreatmentByIdWithError() throws Exception {
        final var expectedId = "id";
        final var expectedMessageError = "Treatment not found";
        when(service.findById(any())).thenThrow(NotFoundException.create("Treatment not found"));

        final var request = get("/v1/treatments/{id}", expectedId)
            .accept(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is4xxClientError())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.message", equalTo(expectedMessageError)));
        verify(service).findById(expectedId);
    }

    @Test
    void shouldGetTreatmentByIds() throws Exception {
        final var expectedId = "id";
        final var expectedDoctorId = "b95a5712-1a2b-4779-8c1d-a99feb3d7831";
        final var expectedPatientId = "3e68df43-17e9-43b5-a744-aa2d52ed7b03";
        final var expectedAppointmentId = "95269d64-e72d-4dfc-956d-f1dc5601e7f2";
        final var expectedDiagnosis = "string diagnosisNN9yg";
        final var expectedPrescription = "string prescriptionuufsn";
        final var expectedNotes = "string notesyYYxL";
        final var expectedOutcome = "string outcomeyqTx8";

        final var actualTreatmentEntity = TreatmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);
        when(service.findByIds(any())).thenReturn(List.of(actualTreatmentEntity));

        final var request = get("/v1/treatments/ids")
            .accept(MediaType.APPLICATION_JSON)
            .param("id", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[0]doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.[0]patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.[0]appointmentId", equalTo(expectedAppointmentId)))
            .andExpect(jsonPath("$.[0]diagnosis", equalTo(expectedDiagnosis)))
            .andExpect(jsonPath("$.[0]prescription", equalTo(expectedPrescription)))
            .andExpect(jsonPath("$.[0]notes", equalTo(expectedNotes)))
            .andExpect(jsonPath("$.[0]outcome", equalTo(expectedOutcome)));
        verify(service).findByIds(List.of(expectedId));
    }

    @Test
    void shouldDeleteTreatment() throws Exception {
        final var expectedId = "id";
        doNothing().when(service).delete(any());

        final var request = delete("/v1/treatments/{id}", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isNoContent());
        verify(service).delete(expectedId);
    }

    @Test
    void shouldNotDeleteTreatmentWithError() throws Exception {
        final var expectedId = "id";
        doThrow(NotFoundException.create("")).when(service).delete(any());

        final var request = delete("/v1/treatments/{id}", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is5xxServerError());
        verify(service).delete(expectedId);
    }

    @Test
    void shouldCreateTreatment() throws Exception {
        final var expectedDoctorId = "ead6827c-342b-4bd1-b0ce-611682535996";
        final var expectedPatientId = "8614b233-d93a-4132-b375-1b7539e416c6";
        final var expectedAppointmentId = "feda9ff8-5885-4753-9e5b-d94ee5d2e300";
        final var expectedDiagnosis = "string diagnosisD4BGX";
        final var expectedPrescription = "string prescription31Z1w";
        final var expectedNotes = "string notesTnZ5y";
        final var expectedOutcome = "string outcome52NKl";

        final var actualTreatmentEntity = TreatmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);
        when(service.save(any())).thenReturn(actualTreatmentEntity);

        final var actualRequest = new TreatmentRequest(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);

        final var request = post("/v1/treatments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(actualRequest));

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isCreated())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.appointmentId", equalTo(expectedAppointmentId)))
            .andExpect(jsonPath("$.diagnosis", equalTo(expectedDiagnosis)))
            .andExpect(jsonPath("$.prescription", equalTo(expectedPrescription)))
            .andExpect(jsonPath("$.notes", equalTo(expectedNotes)))
            .andExpect(jsonPath("$.outcome", equalTo(expectedOutcome)));
        verify(service).save(any());
    }

    @Test
    void shouldUpdateTreatment() throws Exception {
        final var expectedId = "id";
        final var expectedDoctorId = "d77e3798-c188-4426-ba01-ab70df4b3ac5";
        final var expectedPatientId = "39edc6c9-f1c2-4f4a-a6e3-e5cdfcd5ccaa";
        final var expectedAppointmentId = "7dc8fc32-cb64-4926-bf1d-611b1efbeb41";
        final var expectedDiagnosis = "string diagnosiskgtTi";
        final var expectedPrescription = "string prescriptiond1x6K";
        final var expectedNotes = "string notesZhvlJ";
        final var expectedOutcome = "string outcomeSPDEl";

        final var actualTreatmentEntity = TreatmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);
        when(service.update(any(), any())).thenReturn(actualTreatmentEntity);

        final var actualRequest = new TreatmentRequest(expectedDoctorId, expectedPatientId, expectedAppointmentId, expectedDiagnosis,
            expectedPrescription, expectedNotes, expectedOutcome);

        final var request = put("/v1/treatments/{id}", expectedId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(actualRequest));

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.appointmentId", equalTo(expectedAppointmentId)))
            .andExpect(jsonPath("$.diagnosis", equalTo(expectedDiagnosis)))
            .andExpect(jsonPath("$.prescription", equalTo(expectedPrescription)))
            .andExpect(jsonPath("$.notes", equalTo(expectedNotes)))
            .andExpect(jsonPath("$.outcome", equalTo(expectedOutcome)));
        verify(service).update(eq(expectedId), any());
    }

}
