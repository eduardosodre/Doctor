package br.com.project.domain.appointment;

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
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class AppointmentSpecificationsTest {

    @Mock
    private Root<AppointmentEntity> root;
    @Mock
    private CriteriaQuery<AppointmentEntity> query;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    private AppointmentFilter filter;

    @BeforeEach
    public void setUp() {
        filter = AppointmentFilter.create(null, null, null, null, null, null);
    }

    @Test
    public void testFilterWithId() {
        filter.setId("string idyfpt8");

        final var pathId = mock(Path.class);
        when(root.get("id")).thenReturn(pathId);
        final var idUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathId)).thenReturn(idUpperExpressionMock);
        final var idLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(idUpperExpressionMock, "%" + filter.getId().toUpperCase() + "%")).thenReturn(idLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<AppointmentEntity> actual = AppointmentSpecifications.filter(filter);
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
        filter.setDoctorId("a4546d0f-03dd-4081-aef3-7d4c841c3360");

        final var pathDoctorId = mock(Path.class);
        when(root.get("doctorId")).thenReturn(pathDoctorId);
        final var doctorIdPredicateMock = mock(Predicate.class);
        when(criteriaBuilder.equal(pathDoctorId, filter.getDoctorId())).thenReturn(doctorIdPredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<AppointmentEntity> actual = AppointmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("doctorId");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).equal(pathDoctorId, filter.getDoctorId());
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(doctorIdPredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithPatientId() {
        filter.setPatientId("1b7649b2-643c-4c29-b6b9-303325a5454b");

        final var pathPatientId = mock(Path.class);
        when(root.get("patientId")).thenReturn(pathPatientId);
        final var patientIdPredicateMock = mock(Predicate.class);
        when(criteriaBuilder.equal(pathPatientId, filter.getPatientId())).thenReturn(patientIdPredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<AppointmentEntity> actual = AppointmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("patientId");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).equal(pathPatientId, filter.getPatientId());
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(patientIdPredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithAppointmentDate() {
        filter.setInitialAppointmentDate(LocalDateTime.now());
        filter.setFinalAppointmentDate(LocalDateTime.now());

        final var pathInitialAppointmentDate = mock(Path.class);
        when(root.get("appointmentDate")).thenReturn(pathInitialAppointmentDate);
        final var appointmentDatePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.between(pathInitialAppointmentDate, filter.getInitialAppointmentDate(), filter.getFinalAppointmentDate()))
            .thenReturn(appointmentDatePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<AppointmentEntity> actual = AppointmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("appointmentDate");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).between(pathInitialAppointmentDate, filter.getInitialAppointmentDate(), filter.getFinalAppointmentDate());
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(appointmentDatePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithStatus() {
        filter.setStatus("string statusAwS91");

        final var pathStatus = mock(Path.class);
        when(root.get("status")).thenReturn(pathStatus);
        final var statusUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathStatus)).thenReturn(statusUpperExpressionMock);
        final var statusLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(statusUpperExpressionMock, "%" + filter.getStatus().toUpperCase() + "%")).thenReturn(statusLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<AppointmentEntity> actual = AppointmentSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("status");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathStatus);
        verify(criteriaBuilder, times(1)).like(statusUpperExpressionMock, "%" + filter.getStatus().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(statusLikePredicateMock, actualPredicate);
    }

}
