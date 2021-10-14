package org.miage.intervenantservice.entity;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.stereotype.Service;

@Service
public class IntervenantValidator {

  private Validator validator;

  IntervenantValidator(Validator validator) {
    this.validator = validator;
  }

  public void validate(IntervenantInput intervenant) {
    Set<ConstraintViolation<IntervenantInput>> violations = validator.validate(intervenant);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}