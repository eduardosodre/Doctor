package br.com.project.api.v1.patient;

import br.com.project.domain.exceptions.NotFoundException;
import br.com.project.domain.patient.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
	
    @Operation(summary = "Find all Patients")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Patients",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    @GetMapping
    public ResponseEntity<Page<PatientResponse>> findAll(PatientFilterRequest filter, Pageable pageable) {
        final var patientPage = patientService.findAll(filter.toEntity(), pageable);
        final var patientResponses = patientPage.getContent().stream()
            .map(PatientResponse::fromEntity)
            .collect(Collectors.toList());
        final var responsePage = new PageImpl<>(patientResponses, pageable, patientPage.getTotalElements());
        return ResponseEntity.ok(responsePage);
    }

    @Operation(summary = "Get a Patient by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the Patient",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> getPatientById(
        @Parameter(description = "The ID of the Patient to be retrieved") @PathVariable String id) {
        final var patientEntity = patientService.findById(id);
        final var patientResponse = PatientResponse.fromEntity(patientEntity);
        return ResponseEntity.ok(patientResponse);
    }

    @Operation(summary = "Get Patients by IDs")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Patients",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    @GetMapping("/ids")
    public ResponseEntity<List<PatientResponse>> getPatientsByIds(
        @Parameter(description = "List of Patient IDs") @RequestParam("id") List<String> ids) {
        final var patients = patientService.findByIds(ids);
        final var patientResponses = patients.stream()
            .map(PatientResponse::fromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(patientResponses);
    }

    @Operation(summary = "Create a new Patient")
    @ApiResponse(responseCode = "201", description = "Patient created successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponse.class)))
    @PostMapping
    public ResponseEntity<PatientResponse> createPatient(
        @RequestBody PatientRequest request) {
        final var patientEntity = request.toEntity();
        final var savedPatient = patientService.save(patientEntity);
        final var patientResponse = PatientResponse.fromEntity(savedPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
    }

    @Operation(summary = "Update an existing Patient")
    @ApiResponse(responseCode = "200", description = "Patient updated successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = PatientResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> updatePatient(
        @Parameter(description = "The ID of the Patient to be updated") @PathVariable String id,
        @RequestBody PatientRequest request) {
        final var updatedPatientEntity = patientService.update(id, request.toEntity(id));
        return ResponseEntity.ok(PatientResponse.fromEntity(updatedPatientEntity));
    }

    @Operation(summary = "Delete a Patient by ID (Soft Delete)")
    @ApiResponse(responseCode = "204", description = "Patient deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(
        @Parameter(description = "The ID of the Patient to be deleted") @PathVariable String id) {
        try {
            patientService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete a PatientPhone from Patient")
    @ApiResponse(responseCode = "204", description = "PatientPhone deleted successfully")
    @DeleteMapping("/{id}/patientphone/{patientPhoneId}")
    public ResponseEntity<Void> deletePatientPhoneFromPatient(
        @Parameter(description = "The ID of the Patient") @PathVariable String id,
        @Parameter(description = "The ID of the PatientPhone to be deleted") @PathVariable String patientPhoneId) {
        try {
            patientService.deletePatientPhone(id, patientPhoneId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete a PatientAddress from Patient")
    @ApiResponse(responseCode = "204", description = "PatientAddress deleted successfully")
    @DeleteMapping("/{id}/patientaddress/{patientAddressId}")
    public ResponseEntity<Void> deletePatientAddressFromPatient(
        @Parameter(description = "The ID of the Patient") @PathVariable String id,
        @Parameter(description = "The ID of the PatientAddress to be deleted") @PathVariable String patientAddressId) {
        try {
            patientService.deletePatientAddress(id, patientAddressId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Delete a PatientEmail from Patient")
    @ApiResponse(responseCode = "204", description = "PatientEmail deleted successfully")
    @DeleteMapping("/{id}/patientemail/{patientEmailId}")
    public ResponseEntity<Void> deletePatientEmailFromPatient(
        @Parameter(description = "The ID of the Patient") @PathVariable String id,
        @Parameter(description = "The ID of the PatientEmail to be deleted") @PathVariable String patientEmailId) {
        try {
            patientService.deletePatientEmail(id, patientEmailId);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
