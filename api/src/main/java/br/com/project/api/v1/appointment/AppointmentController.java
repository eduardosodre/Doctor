package br.com.project.api.v1.appointment;

import br.com.project.domain.appointment.AppointmentService;
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
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @Operation(summary = "Find all Appointments")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Appointments",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)))
    @GetMapping
    public ResponseEntity<Page<AppointmentResponse>> findAll(AppointmentFilterRequest filter, Pageable pageable) {
        final var appointmentPage = appointmentService.findAll(filter.toEntity(), pageable);
        final var appointmentResponses = appointmentPage.getContent().stream()
            .map(AppointmentResponse::fromEntity)
            .collect(Collectors.toList());
        final var responsePage = new PageImpl<>(appointmentResponses, pageable, appointmentPage.getTotalElements());
        return ResponseEntity.ok(responsePage);
    }

    @Operation(summary = "Get a Appointment by ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the Appointment",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class)))
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(
        @Parameter(description = "The ID of the Appointment to be retrieved") @PathVariable String id) {
        final var appointmentEntity = appointmentService.findById(id);
        final var appointmentResponse = AppointmentResponse.fromEntity(appointmentEntity);
        return ResponseEntity.ok(appointmentResponse);
    }

    @Operation(summary = "Get Appointments by IDs")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved Appointments",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class)))
    @GetMapping("/ids")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByIds(
        @Parameter(description = "List of Appointment IDs") @RequestParam("id") List<String> ids) {
        final var appointments = appointmentService.findByIds(ids);
        final var appointmentResponses = appointments.stream()
            .map(AppointmentResponse::fromEntity)
            .collect(Collectors.toList());
        return ResponseEntity.ok(appointmentResponses);
    }

    @Operation(summary = "Create a new Appointment")
    @ApiResponse(responseCode = "201", description = "Appointment created successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class)))
    @PostMapping
    public ResponseEntity<AppointmentResponse> createAppointment(
        @RequestBody AppointmentRequest request) {
        final var appointmentEntity = request.toEntity();
        final var savedAppointment = appointmentService.save(appointmentEntity);
        final var appointmentResponse = AppointmentResponse.fromEntity(savedAppointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponse);
    }

    @Operation(summary = "Update an existing Appointment")
    @ApiResponse(responseCode = "200", description = "Appointment updated successfully",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = AppointmentResponse.class)))
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> updateAppointment(
        @Parameter(description = "The ID of the Appointment to be updated") @PathVariable String id,
        @RequestBody AppointmentRequest request) {
        final var updatedAppointmentEntity = appointmentService.update(id, request.toEntity(id));
        return ResponseEntity.ok(AppointmentResponse.fromEntity(updatedAppointmentEntity));
    }

    @Operation(summary = "Delete a Appointment by ID (Soft Delete)")
    @ApiResponse(responseCode = "204", description = "Appointment deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(
        @Parameter(description = "The ID of the Appointment to be deleted") @PathVariable String id) {
        try {
            appointmentService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
