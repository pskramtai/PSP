package com.company.main.validation_service;

import com.company.main.models.User;
import com.company.main.validators.EmailValidator;
import com.company.main.validators.PasswordChecker;
import com.company.main.validators.PhoneValidator;

public class UserValidator implements UserValidatorInterface{

    private final EmailValidator emailValidator;
    private final PasswordChecker passwordChecker;
    private final PhoneValidator phoneValidator;

    public UserValidator(EmailValidator emailValidator, PasswordChecker passwordChecker, PhoneValidator phoneValidator){
        this.emailValidator = emailValidator;
        this.passwordChecker = passwordChecker;
        this.phoneValidator = phoneValidator;
    }
    @Override
    public boolean isValid(User user) {
        boolean nameValid = user.getName() != null && !user.getName().isEmpty();
        boolean surnameValid = user.getSurname() != null && !user.getSurname().isEmpty();
        boolean addressValid = user.getAddress() != null && !user.getAddress().isEmpty();
        boolean phoneNumberValid = phoneValidator.isPhoneNumberSymbolsValid(user.getPhoneNumber());
        boolean emailValid = validateEmail(user.getEmail());
        boolean passwordValid = validatePassword(user.getPassword());

        return nameValid && surnameValid && addressValid && phoneNumberValid && emailValid && passwordValid;
    }

    private boolean validateEmail(String email){
        return !emailValidator.isEmailConsistIllegalSymbols(email)
                && emailValidator.isEmailConsistEta(email)
                && emailValidator.isEmailTLDValid(email)
                && emailValidator.isEmailDomainValid(email);
    }

    private boolean validatePassword(String password){
        return passwordChecker.isPasswordLengthValid(password, 11)
                && passwordChecker.isStringContainsSpecialCharacters(password)
                && passwordChecker.isStringContainsUpperCaseSymbol(password);
    }

}
