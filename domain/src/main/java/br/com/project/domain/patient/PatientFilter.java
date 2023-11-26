package br.com.project.domain.patient;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatientFilter {

	private String id;
	private String name;
	private LocalDate initialBirthdate;
	private LocalDate finalBirthdate;
	private String gender;

	public static PatientFilter create(                                     final String id, 
                                     final String name, 
                                     final LocalDate initialBirthdate,
                                     final LocalDate finalBirthdate, 
                                     final String gender) {
		return new PatientFilter(                           id, 
                           name, 
                           initialBirthdate,
                           finalBirthdate, 
                           gender);
	}
}
