package br.com.project.api.v1.treatment;

import br.com.project.domain.exceptions.NotFoundException;
import br.com.project.domain.treatment.TreatmentService;
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
@RequestMapping("/v1/treatments")
@RequiredArgsConstructor
public class TreatmentController {

    private final TreatmentService treatmentService;
	
    @Operation(summary = "Find all Treatments")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Treatments",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    @GetMapping
    public ResponseEntity<Page<TreatmentResponse>> findAll(TreatmentFilterRequest filter, Pageable pageable) {
        final var treatmentPage = treatmentService.findAll(filter.toEntity(), pageable);
        final var treatmentResponses = treatmentPage.getContent().stream()
            .map(TreatmentResponse::fromEntity)
            .collect(Collectors.toList());
        final var responsePage = new PageImpl<>(treatmentResponses, pageable, treatmentPage.getTotalElements());
        return ResponseEntity.ok(responsePage);
    }

    @Operation(summary = "Get a Treatment by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the Treatment",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = TreatmentResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<TreatmentResponse> getTreatmentById(
        @Parameter(description = "The ID of the Treatment to be retrieved") @PathVariable String id) {
        final var treatmentEntity = treatmentService.findById(id);
        final var treatmentResponse = TreatmentResponse.fromEntity(treatmentEntity);
        return ResponseEntity.ok(treatmentResponse);
    }

    @Operation(summary = "Get Treatments by IDs")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Treatments",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    @GetMapping("/ids")
    public ResponseEntity<List<TreatmentResponse>> getTreatmentsByIds(
        @Parameter(description = "List of Treatment IDs") @RequestParam("id") List<String> ids) {
        final var treatments = treatmentService.findByIds(ids);
        final var treatmentResponses = treatments.stream()
            .map(TreatmentResponse::fromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(treatmentResponses);
    }

    @Operation(summary = "Create a new Treatment")
    @ApiResponse(responseCode = "201", description = "Treatment created successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = TreatmentResponse.class)))
    @PostMapping
    public ResponseEntity<TreatmentResponse> createTreatment(
        @RequestBody TreatmentRequest request) {
        final var treatmentEntity = request.toEntity();
        final var savedTreatment = treatmentService.save(treatmentEntity);
        final var treatmentResponse = TreatmentResponse.fromEntity(savedTreatment);
        return ResponseEntity.status(HttpStatus.CREATED).body(treatmentResponse);
    }

    @Operation(summary = "Update an existing Treatment")
    @ApiResponse(responseCode = "200", description = "Treatment updated successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = TreatmentResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<TreatmentResponse> updateTreatment(
        @Parameter(description = "The ID of the Treatment to be updated") @PathVariable String id,
        @RequestBody TreatmentRequest request) {
        final var updatedTreatmentEntity = treatmentService.update(id, request.toEntity(id));
        return ResponseEntity.ok(TreatmentResponse.fromEntity(updatedTreatmentEntity));
    }

    @Operation(summary = "Delete a Treatment by ID (Soft Delete)")
    @ApiResponse(responseCode = "204", description = "Treatment deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatment(
        @Parameter(description = "The ID of the Treatment to be deleted") @PathVariable String id) {
        try {
            treatmentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
