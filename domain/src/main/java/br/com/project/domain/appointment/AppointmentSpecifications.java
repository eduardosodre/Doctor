package br.com.project.domain.appointment;

import br.com.project.domain.util.SpecUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class AppointmentSpecifications {

    public static Specification<AppointmentEntity> filter(AppointmentFilter filter) {
        final var queryList = new ArrayList<Specification<AppointmentEntity>>();

        if (filter.getId() != null) {
            queryList.add(idLike(filter.getId()));
        }
        if (filter.getDoctorId() != null) {
            queryList.add(doctorIdEquals(filter.getDoctorId()));
        }
        if (filter.getPatientId() != null) {
            queryList.add(patientIdEquals(filter.getPatientId()));
        }
        if (filter.getInitialAppointmentDate() != null && filter.getFinalAppointmentDate() != null) {
            queryList.add(appointmentDateBetween(filter.getInitialAppointmentDate(), filter.getFinalAppointmentDate()));
        }
        if (filter.getStatus() != null) {
            queryList.add(statusLike(filter.getStatus()));
        }
        queryList.add(SpecUtil.deletedIsFalse());
        return queryList.stream().reduce(Specification::and).orElse(null);
    }

    private static Specification<AppointmentEntity> idLike(String id) {
        return SpecUtil.like("id", id);
    }

    private static Specification<AppointmentEntity> doctorIdEquals(String doctorId) {
        return SpecUtil.equal("doctorId", doctorId);
    }

    private static Specification<AppointmentEntity> patientIdEquals(String patientId) {
        return SpecUtil.equal("patientId", patientId);
    }

    private static Specification<AppointmentEntity> appointmentDateBetween(LocalDateTime min, LocalDateTime max) {
        return SpecUtil.between("appointmentDate", min, max);
    }

    private static Specification<AppointmentEntity> statusLike(String status) {
        return SpecUtil.like("status", status);
    }

}
