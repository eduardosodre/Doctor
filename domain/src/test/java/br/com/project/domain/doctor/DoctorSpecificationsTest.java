package br.com.project.domain.doctor;

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
public class DoctorSpecificationsTest {

    @Mock
    private Root<DoctorEntity> root;
    @Mock
    private CriteriaQuery<DoctorEntity> query;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    private DoctorFilter filter;

    @BeforeEach
    public void setUp() {
        filter = DoctorFilter.create(null, null, null, null);
    }

    @Test
    public void testFilterWithId() {
        filter.setId("string id3DwPe");

        final var pathId = mock(Path.class);
        when(root.get("id")).thenReturn(pathId);
        final var idUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathId)).thenReturn(idUpperExpressionMock);
        final var idLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(idUpperExpressionMock, "%" + filter.getId().toUpperCase() + "%")).thenReturn(idLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<DoctorEntity> actual = DoctorSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("id");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathId);
        verify(criteriaBuilder, times(1)).like(idUpperExpressionMock, "%" + filter.getId().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(idLikePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithCrm() {
        filter.setCrm("string crmzlV");

        final var pathCrm = mock(Path.class);
        when(root.get("crm")).thenReturn(pathCrm);
        final var crmUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathCrm)).thenReturn(crmUpperExpressionMock);
        final var crmLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(crmUpperExpressionMock, "%" + filter.getCrm().toUpperCase() + "%")).thenReturn(crmLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<DoctorEntity> actual = DoctorSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("crm");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathCrm);
        verify(criteriaBuilder, times(1)).like(crmUpperExpressionMock, "%" + filter.getCrm().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(crmLikePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithName() {
        filter.setName("string nameNz4YPB");

        final var pathName = mock(Path.class);
        when(root.get("name")).thenReturn(pathName);
        final var nameUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathName)).thenReturn(nameUpperExpressionMock);
        final var nameLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(nameUpperExpressionMock, "%" + filter.getName().toUpperCase() + "%")).thenReturn(nameLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<DoctorEntity> actual = DoctorSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("name");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathName);
        verify(criteriaBuilder, times(1)).like(nameUpperExpressionMock, "%" + filter.getName().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(nameLikePredicateMock, actualPredicate);
    }

    @Test
    public void testFilterWithSpecialty() {
        filter.setSpecialty("string specialtyZs0");

        final var pathSpecialty = mock(Path.class);
        when(root.get("specialty")).thenReturn(pathSpecialty);
        final var specialtyUpperExpressionMock = mock(Expression.class);
        when(criteriaBuilder.upper(pathSpecialty)).thenReturn(specialtyUpperExpressionMock);
        final var specialtyLikePredicateMock = mock(Predicate.class);
        when(criteriaBuilder.like(specialtyUpperExpressionMock, "%" + filter.getSpecialty().toUpperCase() + "%")).thenReturn(
            specialtyLikePredicateMock);

        final var pathDeleted = mock(Path.class);
        when(root.get("deletedAt")).thenReturn(pathDeleted);

        Specification<DoctorEntity> actual = DoctorSpecifications.filter(filter);
        final var actualPredicate = actual.toPredicate(root, query, criteriaBuilder);

        verify(root, times(1)).get("specialty");
        verifyNoMoreInteractions(root);

        verify(criteriaBuilder, times(1)).upper(pathSpecialty);
        verify(criteriaBuilder, times(1)).like(specialtyUpperExpressionMock, "%" + filter.getSpecialty().toUpperCase() + "%");
        verify(criteriaBuilder, times(1)).isNull(pathDeleted);

        assertEquals(specialtyLikePredicateMock, actualPredicate);
    }

}
