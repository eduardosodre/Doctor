package br.com.project.domain.patient;

import br.com.project.domain.util.SpecUtil;
import lombok.experimental.UtilityClass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.ArrayList;
import java.math.BigDecimal;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PatientSpecifications {

	public static Specification<PatientEntity> filter(PatientFilter filter) {
        final var queryList = new ArrayList<Specification<PatientEntity>>();
        
		if (filter.getId() != null) {
			queryList.add(idLike(filter.getId()));
		}
		if (filter.getName() != null) {
			queryList.add(nameLike(filter.getName()));
		}
		if (filter.getInitialBirthdate() != null && filter.getFinalBirthdate() != null) {
			queryList.add(birthdateBetween(filter.getInitialBirthdate(), filter.getFinalBirthdate()));
		}
		if (filter.getGender() != null) {
			queryList.add(genderLike(filter.getGender()));
		}
        queryList.add(SpecUtil.deletedIsFalse());
        return queryList.stream().reduce(Specification::and).orElse(null);
    }
	private static Specification<PatientEntity> idLike(String id) {
		return SpecUtil.like("id", id);
	}

	private static Specification<PatientEntity> nameLike(String name) {
		return SpecUtil.like("name", name);
	}

	private static Specification<PatientEntity> birthdateBetween(LocalDate min, LocalDate max) {
		return SpecUtil.between("birthdate", min, max);
	}

	private static Specification<PatientEntity> genderLike(String gender) {
		return SpecUtil.like("gender", gender);
	}

}
