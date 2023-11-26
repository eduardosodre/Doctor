package br.com.project.api.v1.patientemail;

import br.com.project.domain.patientemail.PatientEmailEntity;

public record PatientEmailResponse(String id,
                                   String email) {

    public static PatientEmailResponse fromEntity(PatientEmailEntity entity) {
        return new PatientEmailResponse(
            entity.getId(),
            entity.getEmail());
    }
}
