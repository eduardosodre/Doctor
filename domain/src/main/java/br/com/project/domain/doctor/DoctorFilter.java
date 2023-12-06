package br.com.project.domain.doctor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DoctorFilter {

    private String id;
    private String crm;
    private String name;
    private String specialty;

    public static DoctorFilter create(final String id,
                                      final String crm,
                                      final String name,
                                      final String specialty) {
        return new DoctorFilter(id,
            crm,
            name,
            specialty);
    }
}
