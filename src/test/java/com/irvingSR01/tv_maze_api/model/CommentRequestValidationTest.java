package com.irvingSR01.tv_maze_api.model;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class CommentRequestValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validRequest_hasNoViolations() {
        CommentRequest request = new CommentRequest("Great show!", 3);

        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertThat(violations).isEmpty();
    }

    @Test
    void blankComment_isRejected() {
        CommentRequest request = new CommentRequest("   ", 3);

        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertThat(violations).isNotEmpty();
    }

    @Test
    void nullRating_isRejected() {
        CommentRequest request = new CommentRequest("Great show!", null);

        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertThat(violations)
                .anyMatch(v -> v.getMessage().equals("rating is required"));
    }

    @Test
    void ratingAboveFive_isRejected() {
        CommentRequest request = new CommentRequest("Great show!", 8);

        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertThat(violations)
                .anyMatch(v -> v.getMessage().equals("rating must be between 0 and 5"));
    }

    @Test
    void ratingBelowZero_isRejected() {
        CommentRequest request = new CommentRequest("Great show!", -1);

        Set<ConstraintViolation<CommentRequest>> violations = validator.validate(request);

        assertThat(violations)
                .anyMatch(v -> v.getMessage().equals("rating must be between 0 and 5"));
    }
}