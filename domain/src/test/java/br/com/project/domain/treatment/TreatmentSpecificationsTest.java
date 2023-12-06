package br.com.project.domain.treatment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class TreatmentSpecificationsTest {

    @Mock
    private Root<TreatmentEntity> root;
    @Mock
    private CriteriaQuery<TreatmentEntity> query;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    private TreatmentFilter filter;

    @BeforeEach
    public void setUp() {
        filter = TreatmentFilter.create(null, null, null, null, null, null, null, null);
    }

    @Test
    public void testFilterWithId() {
        filter.setId("string idIT0yi");

        final var pathId = mock(Path.class);
        when(root.get("id")).thenReturn(pathId);
        final var idUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathId)).thenReturn(idUpperExpressionMock);
        final var idLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(idUpperExpressionMock, "%" + filter.getId().toUpperCase() + "%")).thenReturn(idLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<TreatmentEntity> actual = TreatmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("id");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathId);
        verify(criteriaBuilder, times(1)).like(idUpperExpressionMock, "%" + filter.getId().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(idLikePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithDoctorId() {
        filter.setDoctorId("22b2d0e0-3d72-49f6-926c-9e1ae9342872");

        final var pathDoctorId = mock(Path.class);
        when(root.get("doctorId")).thenReturn(pathDoctorId);
        final var doctorIdPredicateMock = mock(Predicate.class);
        when(criteriaBuilder.equal(pathDoctorId, filter.getDoctorId())).thenReturn(doctorIdPredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<TreatmentEntity> actual = TreatmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("doctorId");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).equal(pathDoctorId, filter.getDoctorId());
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(doctorIdPredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithPatientId() {
        filter.setPatientId("6876710f-dc85-48d5-a0c1-4c121f056b22");

        final var pathPatientId = mock(Path.class);
        when(root.get("patientId")).thenReturn(pathPatientId);
        final var patientIdPredicateMock = mock(Predicate.class);
        when(criteriaBuilder.equal(pathPatientId, filter.getPatientId())).thenReturn(patientIdPredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<TreatmentEntity> actual = TreatmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("patientId");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).equal(pathPatientId, filter.getPatientId());
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(patientIdPredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithAppointmentId() {
        filter.setAppointmentId("31de4aca-1fd3-4080-8da2-e9beb3bce46b");

        final var pathAppointmentId = mock(Path.class);
        when(root.get("appointmentId")).thenReturn(pathAppointmentId);
        final var appointmentIdPredicateMock = mock(Predicate.class);
        when(criteriaBuilder.equal(pathAppointmentId, filter.getAppointmentId())).thenReturn(appointmentIdPredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<TreatmentEntity> actual = TreatmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("appointmentId");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).equal(pathAppointmentId, filter.getAppointmentId());
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(appointmentIdPredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithDiagnosis() {
        filter.setDiagnosis("string diagnosisngAp8");

        final var pathDiagnosis = mock(Path.class);
        when(root.get("diagnosis")).thenReturn(pathDiagnosis);
        final var diagnosisUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathDiagnosis)).thenReturn(diagnosisUpperExpressionMock);
        final var diagnosisLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(diagnosisUpperExpressionMock, "%" + filter.getDiagnosis().toUpperCase() + "%")).thenReturn(
            diagnosisLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<TreatmentEntity> actual = TreatmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("diagnosis");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathDiagnosis);
        verify(criteriaBuilder, times(1)).like(diagnosisUpperExpressionMock, "%" + filter.getDiagnosis().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(diagnosisLikePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithPrescription() {
        filter.setPrescription("string prescriptionkVmE4");

        final var pathPrescription = mock(Path.class);
        when(root.get("prescription")).thenReturn(pathPrescription);
        final var prescriptionUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathPrescription)).thenReturn(prescriptionUpperExpressionMock);
        final var prescriptionLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(prescriptionUpperExpressionMock, "%" + filter.getPrescription().toUpperCase() + "%")).thenReturn(
            prescriptionLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<TreatmentEntity> actual = TreatmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("prescription");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathPrescription);
        verify(criteriaBuilder, times(1)).like(prescriptionUpperExpressionMock, "%" + filter.getPrescription().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(prescriptionLikePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithNotes() {
        filter.setNotes("string notesbF9ee");

        final var pathNotes = mock(Path.class);
        when(root.get("notes")).thenReturn(pathNotes);
        final var notesUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathNotes)).thenReturn(notesUpperExpressionMock);
        final var notesLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(notesUpperExpressionMock, "%" + filter.getNotes().toUpperCase() + "%")).thenReturn(notesLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<TreatmentEntity> actual = TreatmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("notes");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathNotes);
        verify(criteriaBuilder, times(1)).like(notesUpperExpressionMock, "%" + filter.getNotes().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(notesLikePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithOutcome() {
        filter.setOutcome("string outcomeTfNzU");

        final var pathOutcome = mock(Path.class);
        when(root.get("outcome")).thenReturn(pathOutcome);
        final var outcomeUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathOutcome)).thenReturn(outcomeUpperExpressionMock);
        final var outcomeLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(outcomeUpperExpressionMock, "%" + filter.getOutcome().toUpperCase() + "%")).thenReturn(outcomeLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<TreatmentEntity> actual = TreatmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("outcome");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathOutcome);
        verify(criteriaBuilder, times(1)).like(outcomeUpperExpressionMock, "%" + filter.getOutcome().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(outcomeLikePredicateMock, actualPredicate);
    }

}
