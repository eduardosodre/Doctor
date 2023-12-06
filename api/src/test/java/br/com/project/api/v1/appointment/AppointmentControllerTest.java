package br.com.project.api.v1.appointment;

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
import br.com.project.domain.appointment.AppointmentEntity;
import br.com.project.domain.appointment.AppointmentService;
import br.com.project.domain.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ControllerTest(controllers = AppointmentController.class)
public class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private AppointmentService service;

    @Test
    void shouldFindAllAppointment() throws Exception {
        final var expectedId = "id";
        final var expectedDoctorId = "0748dde4-20e4-4636-a66e-394ec913dfec";
        final var expectedPatientId = "6d251d0a-511b-4455-9a6f-5f369247579a";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusOwzNc";

        final var actualAppointmentEntity = AppointmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);
        final var page = Pageable.unpaged();
        final var expectedPage = new PageImpl<>(List.of(actualAppointmentEntity), page, 1);
        when(service.findAll(any(), any())).thenReturn(expectedPage);

        final var request = get("/v1/appointments")
            .accept(MediaType.APPLICATION_JSON)
            .param("doctorId", expectedDoctorId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.content[0]doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.content[0]patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.content[0]appointmentDate", equalTo(expectedAppointmentDate.format(DateTimeFormatter.ISO_DATE_TIME))))
            .andExpect(jsonPath("$.content[0]status", equalTo(expectedStatus)));
        verify(service).findAll(any(), any());
    }

    @Test
    void shouldGetAppointmentById() throws Exception {
        final var expectedId = "id";
        final var expectedDoctorId = "ed3cfbbb-2239-403a-a63a-113cd5ec8000";
        final var expectedPatientId = "d04070eb-7b1f-482a-b295-14c332fa847b";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusMvEcs";

        final var actualAppointmentEntity = AppointmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);
        when(service.findById(any())).thenReturn(actualAppointmentEntity);

        final var request = get("/v1/appointments/{id}", expectedId)
            .accept(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.appointmentDate", equalTo(expectedAppointmentDate.format(DateTimeFormatter.ISO_DATE_TIME))))
            .andExpect(jsonPath("$.status", equalTo(expectedStatus)));
        verify(service).findById(expectedId);
    }

    @Test
    void shouldGetAppointmentByIdWithError() throws Exception {
        final var expectedId = "id";
        final var expectedMessageError = "Appointment not found";
        when(service.findById(any())).thenThrow(NotFoundException.create("Appointment not found"));

        final var request = get("/v1/appointments/{id}", expectedId)
            .accept(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is4xxClientError())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.message", equalTo(expectedMessageError)));
        verify(service).findById(expectedId);
    }

    @Test
    void shouldGetAppointmentByIds() throws Exception {
        final var expectedId = "id";
        final var expectedDoctorId = "aaee24dd-5f07-45a9-bc1e-e399765d6059";
        final var expectedPatientId = "f34ceaae-233a-4716-928f-bcceebe7e96a";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusn9u3K";

        final var actualAppointmentEntity = AppointmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);
        when(service.findByIds(any())).thenReturn(List.of(actualAppointmentEntity));

        final var request = get("/v1/appointments/ids")
            .accept(MediaType.APPLICATION_JSON)
            .param("id", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[0]doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.[0]patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.[0]appointmentDate", equalTo(expectedAppointmentDate.format(DateTimeFormatter.ISO_DATE_TIME))))
            .andExpect(jsonPath("$.[0]status", equalTo(expectedStatus)));
        verify(service).findByIds(List.of(expectedId));
    }

    @Test
    void shouldDeleteAppointment() throws Exception {
        final var expectedId = "id";
        doNothing().when(service).delete(any());

        final var request = delete("/v1/appointments/{id}", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isNoContent());
        verify(service).delete(expectedId);
    }

    @Test
    void shouldNotDeleteAppointmentWithError() throws Exception {
        final var expectedId = "id";
        doThrow(NotFoundException.create("")).when(service).delete(any());

        final var request = delete("/v1/appointments/{id}", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is5xxServerError());
        verify(service).delete(expectedId);
    }

    @Test
    void shouldCreateAppointment() throws Exception {
        final var expectedDoctorId = "8572d7ba-42ec-4acb-bc16-28ca07d4b9d6";
        final var expectedPatientId = "634daedc-59e2-46c0-a820-b299ed60ed63";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string statusR54jW";

        final var actualAppointmentEntity = AppointmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);
        when(service.save(any())).thenReturn(actualAppointmentEntity);

        final var actualRequest = new AppointmentRequest(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);

        final var request = post("/v1/appointments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(actualRequest));

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isCreated())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.appointmentDate", equalTo(expectedAppointmentDate.format(DateTimeFormatter.ISO_DATE_TIME))))
            .andExpect(jsonPath("$.status", equalTo(expectedStatus)));
        verify(service).save(any());
    }

    @Test
    void shouldUpdateAppointment() throws Exception {
        final var expectedId = "id";
        final var expectedDoctorId = "35e7af65-110d-45fe-97e5-295862eb5e2b";
        final var expectedPatientId = "b5d8a89f-935f-4ead-ab57-80c30f0cec84";
        final var expectedAppointmentDate = LocalDateTime.now();
        final var expectedStatus = "string status33IjF";

        final var actualAppointmentEntity = AppointmentEntity.create(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);
        when(service.update(any(), any())).thenReturn(actualAppointmentEntity);

        final var actualRequest = new AppointmentRequest(expectedDoctorId, expectedPatientId, expectedAppointmentDate, expectedStatus);

        final var request = put("/v1/appointments/{id}", expectedId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(actualRequest));

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.doctorId", equalTo(expectedDoctorId)))
            .andExpect(jsonPath("$.patientId", equalTo(expectedPatientId)))
            .andExpect(jsonPath("$.appointmentDate", equalTo(expectedAppointmentDate.format(DateTimeFormatter.ISO_DATE_TIME))))
            .andExpect(jsonPath("$.status", equalTo(expectedStatus)));
        verify(service).update(eq(expectedId), any());
    }

}
