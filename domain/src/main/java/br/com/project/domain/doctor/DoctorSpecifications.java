package br.com.project.domain.doctor;

import br.com.project.domain.util.SpecUtil;
import java.util.ArrayList;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class DoctorSpecifications {

    public static Specification<DoctorEntity> filter(DoctorFilter filter) {
        final var queryList = new ArrayList<Specification<DoctorEntity>>();

        if (filter.getId() != null) {
            queryList.add(idLike(filter.getId()));
        }
        if (filter.getCrm() != null) {
            queryList.add(crmLike(filter.getCrm()));
        }
        if (filter.getName() != null) {
            queryList.add(nameLike(filter.getName()));
        }
        if (filter.getSpecialty() != null) {
            queryList.add(specialtyLike(filter.getSpecialty()));
        }
        queryList.add(SpecUtil.deletedIsFalse());
        return queryList.stream().reduce(Specification::and).orElse(null);
    }

    private static Specification<DoctorEntity> idLike(String id) {
        return SpecUtil.like("id", id);
    }

    private static Specification<DoctorEntity> crmLike(String crm) {
        return SpecUtil.like("crm", crm);
    }

    private static Specification<DoctorEntity> nameLike(String name) {
        return SpecUtil.like("name", name);
    }

    private static Specification<DoctorEntity> specialtyLike(String specialty) {
        return SpecUtil.like("specialty", specialty);
    }

}
