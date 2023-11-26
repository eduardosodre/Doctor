package br.com.project.domain.treatment;

import br.com.project.domain.util.SpecUtil;
import java.util.ArrayList;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class TreatmentSpecifications {
 
    public static Specification<TreatmentEntity> filter(TreatmentFilter filter) {
        final var queryList = new ArrayList<Specification<TreatmentEntity>>();

        if (filter.getId() != null) {
            queryList.add(idLike(filter.getId()));
        }
        if (filter.getDoctorId() != null) {
            queryList.add(doctorIdEquals(filter.getDoctorId()));
        }
        if (filter.getPatientId() != null) {
            queryList.add(patientIdEquals(filter.getPatientId()));
        }
        if (filter.getAppointmentId() != null) {
            queryList.add(appointmentIdEquals(filter.getAppointmentId()));
        }
        if (filter.getDiagnosis() != null) {
            queryList.add(diagnosisLike(filter.getDiagnosis()));
        }
        if (filter.getPrescription() != null) {
            queryList.add(prescriptionLike(filter.getPrescription()));
        }
        if (filter.getNotes() != null) {
            queryList.add(notesLike(filter.getNotes()));
        }
        if (filter.getOutcome() != null) {
            queryList.add(outcomeLike(filter.getOutcome()));
        }
        queryList.add(SpecUtil.deletedIsFalse());
        return queryList.stream().reduce(Specification::and).orElse(null);
    }

    private static Specification<TreatmentEntity> idLike(String id) {
        return SpecUtil.like("id", id);
    }

    private static Specification<TreatmentEntity> doctorIdEquals(String doctorId) {
        return SpecUtil.equal("doctorId", doctorId);
    }

    private static Specification<TreatmentEntity> patientIdEquals(String patientId) {
        return SpecUtil.equal("patientId", patientId);
    }

    private static Specification<TreatmentEntity> appointmentIdEquals(String appointmentId) {
        return SpecUtil.equal("appointmentId", appointmentId);
    }

    private static Specification<TreatmentEntity> diagnosisLike(String diagnosis) {
        return SpecUtil.like("diagnosis", diagnosis);
    }

    private static Specification<TreatmentEntity> prescriptionLike(String prescription) {
        return SpecUtil.like("prescription", prescription);
    }

    private static Specification<TreatmentEntity> notesLike(String notes) {
        return SpecUtil.like("notes", notes);
    }

    private static Specification<TreatmentEntity> outcomeLike(String outcome) {
        return SpecUtil.like("outcome", outcome);
    }

}
