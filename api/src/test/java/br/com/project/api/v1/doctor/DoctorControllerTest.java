package br.com.project.api.v1.doctor;

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
import br.com.project.domain.doctor.DoctorEntity;
import br.com.project.domain.doctor.DoctorService;
import br.com.project.domain.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@ControllerTest(controllers = DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private DoctorService service;

    @Test
    void shouldFindAllDoctor() throws Exception {
        final var expectedId = "id";
        final var expectedCrm = "string crm4hk";
        final var expectedName = "string nameWRxGzO";
        final var expectedSpecialty = "string specialtytm7";

        final var actualDoctorEntity = DoctorEntity.create(expectedCrm, expectedName, expectedSpecialty);
        final var page = Pageable.unpaged();
        final var expectedPage = new PageImpl<>(List.of(actualDoctorEntity), page, 1);
        when(service.findAll(any(), any())).thenReturn(expectedPage);

        final var request = get("/v1/doctors")
            .accept(MediaType.APPLICATION_JSON)
            .param("crm", expectedCrm);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.content[0]crm", equalTo(expectedCrm)))
            .andExpect(jsonPath("$.content[0]name", equalTo(expectedName)))
            .andExpect(jsonPath("$.content[0]specialty", equalTo(expectedSpecialty)));
        verify(service).findAll(any(), any());
    }

    @Test
    void shouldGetDoctorById() throws Exception {
        final var expectedId = "id";
        final var expectedCrm = "string crmrjv";
        final var expectedName = "string namevshuGH";
        final var expectedSpecialty = "string specialtyZcT";

        final var actualDoctorEntity = DoctorEntity.create(expectedCrm, expectedName, expectedSpecialty);
        when(service.findById(any())).thenReturn(actualDoctorEntity);

        final var request = get("/v1/doctors/{id}", expectedId)
            .accept(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.crm", equalTo(expectedCrm)))
            .andExpect(jsonPath("$.name", equalTo(expectedName)))
            .andExpect(jsonPath("$.specialty", equalTo(expectedSpecialty)));
        verify(service).findById(expectedId);
    }

    @Test
    void shouldGetDoctorByIdWithError() throws Exception {
        final var expectedId = "id";
        final var expectedMessageError = "Doctor not found";
        when(service.findById(any())).thenThrow(NotFoundException.create("Doctor not found"));

        final var request = get("/v1/doctors/{id}", expectedId)
            .accept(MediaType.APPLICATION_JSON);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is4xxClientError())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.message", equalTo(expectedMessageError)));
        verify(service).findById(expectedId);
    }

    @Test
    void shouldGetDoctorByIds() throws Exception {
        final var expectedId = "id";
        final var expectedCrm = "string crmOM7";
        final var expectedName = "string namec2I2Jx";
        final var expectedSpecialty = "string specialtygDt";

        final var actualDoctorEntity = DoctorEntity.create(expectedCrm, expectedName, expectedSpecialty);
        when(service.findByIds(any())).thenReturn(List.of(actualDoctorEntity));

        final var request = get("/v1/doctors/ids")
            .accept(MediaType.APPLICATION_JSON)
            .param("id", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[0]crm", equalTo(expectedCrm)))
            .andExpect(jsonPath("$.[0]name", equalTo(expectedName)))
            .andExpect(jsonPath("$.[0]specialty", equalTo(expectedSpecialty)));
        verify(service).findByIds(List.of(expectedId));
    }

    @Test
    void shouldDeleteDoctor() throws Exception {
        final var expectedId = "id";
        doNothing().when(service).delete(any());

        final var request = delete("/v1/doctors/{id}", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isNoContent());
        verify(service).delete(expectedId);
    }

    @Test
    void shouldNotDeleteDoctorWithError() throws Exception {
        final var expectedId = "id";
        doThrow(NotFoundException.create("")).when(service).delete(any());

        final var request = delete("/v1/doctors/{id}", expectedId);

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().is5xxServerError());
        verify(service).delete(expectedId);
    }

    @Test
    void shouldCreateDoctor() throws Exception {
        final var expectedCrm = "string crmpoP";
        final var expectedName = "string nameGP9yuc";
        final var expectedSpecialty = "string specialtyKPn";

        final var actualDoctorEntity = DoctorEntity.create(expectedCrm, expectedName, expectedSpecialty);
        when(service.save(any())).thenReturn(actualDoctorEntity);

        final var actualRequest = new DoctorRequest(expectedCrm, expectedName, expectedSpecialty);

        final var request = post("/v1/doctors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(actualRequest));

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isCreated())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.crm", equalTo(expectedCrm)))
            .andExpect(jsonPath("$.name", equalTo(expectedName)))
            .andExpect(jsonPath("$.specialty", equalTo(expectedSpecialty)));
        verify(service).save(any());
    }

    @Test
    void shouldUpdateDoctor() throws Exception {
        final var expectedId = "id";
        final var expectedCrm = "string crmAKE";
        final var expectedName = "string nameGiNVtZ";
        final var expectedSpecialty = "string specialtyiYO";

        final var actualDoctorEntity = DoctorEntity.create(expectedCrm, expectedName, expectedSpecialty);
        when(service.update(any(), any())).thenReturn(actualDoctorEntity);

        final var actualRequest = new DoctorRequest(expectedCrm, expectedName, expectedSpecialty);

        final var request = put("/v1/doctors/{id}", expectedId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(actualRequest));

        final var response = this.mvc.perform(request)
            .andDo(print());

        response.andExpect(status().isOk())
            .andExpect(header().string("Content-Type", MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.crm", equalTo(expectedCrm)))
            .andExpect(jsonPath("$.name", equalTo(expectedName)))
            .andExpect(jsonPath("$.specialty", equalTo(expectedSpecialty)));
        verify(service).update(eq(expectedId), any());
    }

}
