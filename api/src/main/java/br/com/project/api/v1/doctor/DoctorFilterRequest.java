package br.com.project.api.v1.doctor;

import br.com.project.domain.doctor.DoctorFilter;
 
public record DoctorFilterRequest(String id,
                                  String crm,
                                  String name,
                                  String specialty) {

    public DoctorFilter toEntity() {
        return DoctorFilter.create(id,
            crm,
            name,
            specialty);
    }

}
