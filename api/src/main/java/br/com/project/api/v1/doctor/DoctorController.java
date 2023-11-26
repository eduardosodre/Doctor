package br.com.project.api.v1.doctor;

import br.com.project.domain.doctor.DoctorService;
import br.com.project.domain.exceptions.NotFoundException;
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
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @Operation(summary = "Find all Doctors")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Doctors",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    @GetMapping
    public ResponseEntity<Page<DoctorResponse>> findAll(DoctorFilterRequest filter, Pageable pageable) {
        final var doctorPage = doctorService.findAll(filter.toEntity(), pageable);
        final var doctorResponses = doctorPage.getContent().stream()
            .map(DoctorResponse::fromEntity)
            .collect(Collectors.toList());
        final var responsePage = new PageImpl<>(doctorResponses, pageable, doctorPage.getTotalElements());
        return ResponseEntity.ok(responsePage);
    }

    @Operation(summary = "Get a Doctor by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the Doctor",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> getDoctorById(
        @Parameter(description = "The ID of the Doctor to be retrieved") @PathVariable String id) {
        final var doctorEntity = doctorService.findById(id);
        final var doctorResponse = DoctorResponse.fromEntity(doctorEntity);
        return ResponseEntity.ok(doctorResponse);
    }

    @Operation(summary = "Get Doctors by IDs")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Doctors",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    @GetMapping("/ids")
    public ResponseEntity<List<DoctorResponse>> getDoctorsByIds(
        @Parameter(description = "List of Doctor IDs") @RequestParam("id") List<String> ids) {
        final var doctors = doctorService.findByIds(ids);
        final var doctorResponses = doctors.stream()
            .map(DoctorResponse::fromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(doctorResponses);
    }

    @Operation(summary = "Create a new Doctor")
    @ApiResponse(responseCode = "201", description = "Doctor created successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorResponse.class)))
    @PostMapping
    public ResponseEntity<DoctorResponse> createDoctor(
        @RequestBody DoctorRequest request) {
        final var doctorEntity = request.toEntity();
        final var savedDoctor = doctorService.save(doctorEntity);
        final var doctorResponse = DoctorResponse.fromEntity(savedDoctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorResponse);
    }

    @Operation(summary = "Update an existing Doctor")
    @ApiResponse(responseCode = "200", description = "Doctor updated successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = DoctorResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> updateDoctor(
        @Parameter(description = "The ID of the Doctor to be updated") @PathVariable String id,
        @RequestBody DoctorRequest request) {
        final var updatedDoctorEntity = doctorService.update(id, request.toEntity(id));
        return ResponseEntity.ok(DoctorResponse.fromEntity(updatedDoctorEntity));
    }

    @Operation(summary = "Delete a Doctor by ID (Soft Delete)")
    @ApiResponse(responseCode = "204", description = "Doctor deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(
        @Parameter(description = "The ID of the Doctor to be deleted") @PathVariable String id) {
        try {
            doctorService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
