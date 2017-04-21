package com.hellokoding.springboot.validation;

import com.hellokoding.springboot.Cosmonaut;
import com.hellokoding.springboot.service.CosmonautService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CosmonautFormValidator implements Validator {

    @Autowired
    CosmonautService CosmonautService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Cosmonaut.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Cosmonaut cosmonaut = (Cosmonaut) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.cosmonautForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "NotEmpty.cosmonautForm.surname");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "NotEmpty.cosmonautForm.birthday");

    }
}