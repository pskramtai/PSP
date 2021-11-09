package UserValidatorTests;

import com.company.main.models.User;
import com.company.main.validation_service.UserValidator;
import com.company.main.validators.EmailValidator;
import com.company.main.validators.PasswordChecker;
import com.company.main.validators.PhoneValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {
    User user;
    EmailValidator emailValidator = new EmailValidator();
    PasswordChecker passwordChecker = new PasswordChecker();
    PhoneValidator phoneValidator = new PhoneValidator();
    UserValidator userValidator = new UserValidator(emailValidator, passwordChecker, phoneValidator);

    @Test
    public void UserValidation_InvalidEmail_returnFalse(){
        user = new User(1, "name", "surname", "861234567", "addressgmail.com", "address", "A@1ffffffffffffff");
        assertFalse(userValidator.isValid(user));
    }
    @Test
    public void UserValidations_InvalidPhoneNumber_returnFalse(){
        user = new User(1, "name", "surname", "8612367", "address@gmail.com", "address", "A@1ffffffffffffff");
        assertFalse(userValidator.isValid(user));
    }
    @Test
    public void UserValidations_InvalidPassword_returnFalse(){
        user = new User(1, "name", "surname", "861234567", "address@gmail.com", "address", "A1ffffffffffffff");
        assertFalse(userValidator.isValid(user));
    }
    @Test
    public void UserValidations_InvalidName_returnFalse(){
        user = new User(1, "", "surname", "861234567", "address@gmail.com", "address", "A@1ffffffffffffff");
        assertFalse(userValidator.isValid(user));
    }
    @Test
    public void UserValidations_InvalidSurname_returnFalse(){
        user = new User(1, "name", "", "861234567", "address@gmail.com", "address", "A@1ffffffffffffff");
        assertFalse(userValidator.isValid(user));
    }
    @Test
    public void UserValidations_InvalidAddress_returnFalse(){
        user = new User(1, "name", "surname", "861234567", "address@gmail.com", "", "A@1ffffffffffffff");
        assertFalse(userValidator.isValid(user));
    }
    @Test
    public void UserValidations_UserValid_returnTrue(){
        user = new User(1, "name", "surname", "861234567", "address@gmail.com", "address", "A@1ffffffffffffff");
        assertTrue(userValidator.isValid(user));
    }
}