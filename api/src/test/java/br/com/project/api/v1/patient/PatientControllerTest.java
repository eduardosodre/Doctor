package br.com.project.api.v1.patient;

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
import br.com.project.api.v1.patientaddress.PatientAddressRequest;
import br.com.project.api.v1.patientemail.PatientEmailRequest;
import br.com.project.api.v1.patientphone.PatientPhoneRequest;
import br.com.project.domain.exceptions.NotFoundException;
import br.com.project.domain.patient.PatientEntity;
import br.com.project.domain.patient.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ControllerTest(controllers = PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private PatientService service;

    @Test
    void shouldFindAllPatient() throws Exception {
        final var expectedId = "id";
        final var expectedName = "string namexLwjlg";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string gendert7RAd";

        final var actualPatientEntity = PatientEntity.create(expectedName, expectedBirthdate, expectedGender);
        final var page = Pageable.unpaged();
        final var expectedPage = new PageImpl<>(List.of(actualPatientEntity), page, 1);
        when(service.findAll(any(), any())).thenReturn(expectedPage);

        final var request = get("/v1/patients")
            .accept(MediaType.APPLICATION_JSON)
            .param("name", expectedName);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.content[0]name", equalTo(expectedName)))
            .andExpect(jsonPath("$.content[0]birthdate", equalTo(expectedBirthdate.toString())))
            .andExpect(jsonPath("$.content[0]gender", equalTo(expectedGender)));
        verify(service).findAll(any(), any());
    }

    @Test
    void shouldGetPatientById() throws Exception {
        final var expectedId = "id";
        final var expectedName = "string namej8TpbU";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderyldqB";

        final var actualPatientEntity = PatientEntity.create(expectedName, expectedBirthdate, expectedGender);
        when(service.findById(any())).thenReturn(actualPatientEntity);

        final var request = get("/v1/patients/{id}", expectedId)
            .accept(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.name", equalTo(expectedName)))
            .andExpect(jsonPath("$.birthdate", equalTo(expectedBirthdate.toString())))
            .andExpect(jsonPath("$.gender", equalTo(expectedGender)));
        verify(service).findById(expectedId);
    }

    @Test
    void shouldGetPatientByIdWithError() throws Exception {
        final var expectedId = "id";
        final var expectedMessageError = "Patient not found";
        when(service.findById(any())).thenThrow(NotFoundException.create("Patient not found"));

        final var request = get("/v1/patients/{id}", expectedId)
            .accept(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is4xxClientError())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.message", equalTo(expectedMessageError)));
        verify(service).findById(expectedId);
    }

    @Test
    void shouldGetPatientByIds() throws Exception {
        final var expectedId = "id";
        final var expectedName = "string namesC7DKb";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderJazBv";

        final var actualPatientEntity = PatientEntity.create(expectedName, expectedBirthdate, expectedGender);
        when(service.findByIds(any())).thenReturn(List.of(actualPatientEntity));

        final var request = get("/v1/patients/ids")
            .accept(MediaType.APPLICATION_JSON)
            .param("id", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[0]name", equalTo(expectedName)))
            .andExpect(jsonPath("$.[0]birthdate", equalTo(expectedBirthdate.toString())))
            .andExpect(jsonPath("$.[0]gender", equalTo(expectedGender)));
        verify(service).findByIds(List.of(expectedId));
    }

    @Test
    void shouldDeletePatient() throws Exception {
        final var expectedId = "id";
        doNothing().when(service).delete(any());

        final var request = delete("/v1/patients/{id}", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isNoContent());
        verify(service).delete(expectedId);
    }

    @Test
    void shouldDeletePatientPhone() throws Exception {
        final var expectedId = "id";
        final var expectedPatientPhoneId = "id";
        doNothing().when(service).deletePatientPhone(any(), any());

        final var request = delete("/v1/patients/{id}/patientphone/{patientPhoneId}", expectedId, expectedPatientPhoneId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isNoContent());
        verify(service).deletePatientPhone(expectedId, expectedPatientPhoneId);
    }

    @Test
    void shouldDeletePatientPhoneWithNotFound() throws Exception {
        final var expectedId = "id";
        final var expectedPatientPhoneId = "id";
        doThrow(NotFoundException.create("")).when(service).deletePatientPhone(any(), any());

        final var request = delete("/v1/patients/{id}/patientphone/{patientPhoneId}", expectedId, expectedPatientPhoneId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is5xxServerError());
        verify(service).deletePatientPhone(expectedId, expectedPatientPhoneId);
    }

    @Test
    void shouldDeletePatientAddress() throws Exception {
        final var expectedId = "id";
        final var expectedPatientAddressId = "id";
        doNothing().when(service).deletePatientAddress(any(), any());

        final var request = delete("/v1/patients/{id}/patientaddress/{patientAddressId}", expectedId, expectedPatientAddressId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isNoContent());
        verify(service).deletePatientAddress(expectedId, expectedPatientAddressId);
    }

    @Test
    void shouldDeletePatientAddressWithNotFound() throws Exception {
        final var expectedId = "id";
        final var expectedPatientAddressId = "id";
        doThrow(NotFoundException.create("")).when(service).deletePatientAddress(any(), any());

        final var request = delete("/v1/patients/{id}/patientaddress/{patientAddressId}", expectedId, expectedPatientAddressId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is5xxServerError());
        verify(service).deletePatientAddress(expectedId, expectedPatientAddressId);
    }

    @Test
    void shouldDeletePatientEmail() throws Exception {
        final var expectedId = "id";
        final var expectedPatientEmailId = "id";
        doNothing().when(service).deletePatientEmail(any(), any());

        final var request = delete("/v1/patients/{id}/patientemail/{patientEmailId}", expectedId, expectedPatientEmailId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isNoContent());
        verify(service).deletePatientEmail(expectedId, expectedPatientEmailId);
    }

    @Test
    void shouldDeletePatientEmailWithNotFound() throws Exception {
        final var expectedId = "id";
        final var expectedPatientEmailId = "id";
        doThrow(NotFoundException.create("")).when(service).deletePatientEmail(any(), any());

        final var request = delete("/v1/patients/{id}/patientemail/{patientEmailId}", expectedId, expectedPatientEmailId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is5xxServerError());
        verify(service).deletePatientEmail(expectedId, expectedPatientEmailId);
    }

    @Test
    void shouldNotDeletePatientWithError() throws Exception {
        final var expectedId = "id";
        doThrow(NotFoundException.create("")).when(service).delete(any());

        final var request = delete("/v1/patients/{id}", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is5xxServerError());
        verify(service).delete(expectedId);
    }

    @Test
    void shouldCreatePatient() throws Exception {
        final var expectedName = "string namehyiSbf";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderQtXAP";

        final var actualPatientEntity = PatientEntity.create(expectedName, expectedBirthdate, expectedGender);
        when(service.save(any())).thenReturn(actualPatientEntity);

        final var expectedPhones = List.<PatientPhoneRequest>of();
        final var expectedAddresses = List.<PatientAddressRequest>of();
        final var expectedEmails = List.<PatientEmailRequest>of();
        final var actualRequest = new PatientRequest(expectedName, expectedBirthdate, expectedGender, expectedPhones, expectedAddresses,
            expectedEmails);

        final var request = post("/v1/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(actualRequest));

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isCreated())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.name", equalTo(expectedName)))
            .andExpect(jsonPath("$.birthdate", equalTo(expectedBirthdate.toString())))
            .andExpect(jsonPath("$.gender", equalTo(expectedGender)));
        verify(service).save(any());
    }

    @Test
    void shouldUpdatePatient() throws Exception {
        final var expectedId = "id";
        final var expectedName = "string nameRnN247";
        final var expectedBirthdate = LocalDate.now();
        final var expectedGender = "string genderTqFgf";

        final var actualPatientEntity = PatientEntity.create(expectedName, expectedBirthdate, expectedGender);
        when(service.update(any(), any())).thenReturn(actualPatientEntity);

        final var expectedPhones = List.<PatientPhoneRequest>of();
        final var expectedAddresses = List.<PatientAddressRequest>of();
        final var expectedEmails = List.<PatientEmailRequest>of();
        final var actualRequest = new PatientRequest(expectedName, expectedBirthdate, expectedGender, expectedPhones, expectedAddresses,
            expectedEmails);

        final var request = put("/v1/patients/{id}", expectedId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(actualRequest));

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.name", equalTo(expectedName)))
            .andExpect(jsonPath("$.birthdate", equalTo(expectedBirthdate.toString())))
            .andExpect(jsonPath("$.gender", equalTo(expectedGender)));
        verify(service).update(eq(expectedId), any());
    }

}
