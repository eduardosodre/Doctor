package br.com.project.domain.patient;

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
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

@ExtendWith(MockitoExtension.class)
public class PatientSpecificationsTest {

    @Mock
    private Root<PatientEntity> root;
    @Mock
    private CriteriaQuery<PatientEntity> query;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    private PatientFilter filter;

    @BeforeEach
    public void setUp() {
        filter = PatientFilter.create(null, null, null, null, null);
    }

    @Test
    public void testFilterWithId() {
        filter.setId("string idy21Op");

        final var pathId = mock(Path.class);
        when(root.get("id")).thenReturn(pathId);
        final var idUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathId)).thenReturn(idUpperExpressionMock);
        final var idLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(idUpperExpressionMock, "%" + filter.getId().toUpperCase() + "%")).thenReturn(idLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<PatientEntity> actual = PatientSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("id");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathId);
        verify(criteriaBuilder, times(1)).like(idUpperExpressionMock, "%" + filter.getId().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(idLikePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithName() {
        filter.setName("string name3itJLp");

        final var pathName = mock(Path.class);
        when(root.get("name")).thenReturn(pathName);
        final var nameUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathName)).thenReturn(nameUpperExpressionMock);
        final var nameLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(nameUpperExpressionMock, "%" + filter.getName().toUpperCase() + "%")).thenReturn(nameLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<PatientEntity> actual = PatientSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("name");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathName);
        verify(criteriaBuilder, times(1)).like(nameUpperExpressionMock, "%" + filter.getName().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(nameLikePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithBirthdate() {
        filter.setInitialBirthdate(LocalDate.now());
        filter.setFinalBirthdate(LocalDate.now());

        final var pathInitialBirthdate = mock(Path.class);
        when(root.get("birthdate")).thenReturn(pathInitialBirthdate);
        final var birthdatePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.between(pathInitialBirthdate, filter.getInitialBirthdate(), filter.getFinalBirthdate()))
            .thenReturn(birthdatePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<PatientEntity> actual = PatientSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("birthdate");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).between(pathInitialBirthdate, filter.getInitialBirthdate(), filter.getFinalBirthdate());
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(birthdatePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithGender() {
        filter.setGender("string genderCbMJj");

        final var pathGender = mock(Path.class);
        when(root.get("gender")).thenReturn(pathGender);
        final var genderUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathGender)).thenReturn(genderUpperExpressionMock);
        final var genderLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(genderUpperExpressionMock, "%" + filter.getGender().toUpperCase() + "%")).thenReturn(genderLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<PatientEntity> actual = PatientSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("gender");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathGender);
        verify(criteriaBuilder, times(1)).like(genderUpperExpressionMock, "%" + filter.getGender().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(genderLikePredicateMock, actualPredicate);
    }

}
