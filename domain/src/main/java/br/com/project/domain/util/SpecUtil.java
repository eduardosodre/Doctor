package br.com.project.domain.util;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;

public class SpecUtil {

    public static String like(final String term) {
        if (term == null) {
            return null;
        }
        return "%" + term + "%";
    }

    public static <T> Specification<T> like(String fieldName, String value) {
        return (root, query, builder) -> builder.like(builder.upper(root.get(fieldName)),
            like(value.toUpperCase()));
    }

    public static <T> Specification<T> equal(String fieldName, Object value) {
        return (root, query, builder) -> builder.equal(root.get(fieldName), value);
    }

    public static <T> Specification<T> in(String fieldName, List<?> values) {
        return (root, query, builder) -> builder.in(root.get(fieldName)).value(values);
    }

    public static <T> Specification<T> isFalse(String fieldName) {
        return (root, query, builder) -> builder.isFalse(root.get(fieldName));
    }

    public static <T> Specification<T> isTrue(String fieldName) {
        return (root, query, builder) -> builder.isTrue(root.get(fieldName));
    }

    public static <T> Specification<T> isNull(String fieldName) {
        return (root, query, builder) -> builder.isNull(root.get(fieldName));
    }

    public static <T> Specification<T> isNotNull(String fieldName) {
        return (root, query, builder) -> builder.isNotNull(root.get(fieldName));
    }

    public static <T> Specification<T> deletedIsFalse() {
        return isNull("deletedAt");
    }

    public static <T> Specification<T> deletedIsTrue() {
        return isNotNull("deletedAt");
    }

    public static <T> Specification<T> between(String fieldName, BigDecimal initialalue,
                                               BigDecimal finalValue) {
        return (root, query, builder) -> builder
            .between(root.get(fieldName), initialalue, finalValue);
    }
    public static <T> Specification<T> between(String fieldName,
                                               LocalDateTime initialalue,
                                               LocalDateTime finalValue) {
        return (root, query, builder) -> builder
            .between(root.get(fieldName), initialalue, finalValue);
    }

    public static <T> Specification<T> between(String fieldName,
                                               LocalDate initialalue,
                                               LocalDate finalValue) {
        return (root, query, builder) -> builder
            .between(root.get(fieldName), initialalue, finalValue);
    }}