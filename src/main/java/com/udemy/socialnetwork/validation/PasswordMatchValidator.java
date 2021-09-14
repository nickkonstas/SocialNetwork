package com.udemy.socialnetwork.validation;

import com.udemy.socialnetwork.model.entity.AppUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, AppUser> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(AppUser user, ConstraintValidatorContext context) {
        String plainPassword = user.getPlainPassword();;
        String repeatPassword = user.getRepeatPassword();

        //This means that a user is updated and don't come from the registration page
        //aka the validation should pass
        if(plainPassword == null || plainPassword.length() == 0) {
            return  true;
        }

        if(plainPassword == null || !plainPassword.equals(repeatPassword)) {
            return false;
        }
        return true;
    }
}
