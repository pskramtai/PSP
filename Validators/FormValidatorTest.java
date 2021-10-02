

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormValidatorTest {

    //Password Checker
    @Test
    void passwordCheckerLengthTest() {
        assertAll(() -> assertFalse(FormValidator.checkPassword("1!A4", 5)),
                () -> assertTrue(FormValidator.checkPassword("1!A55", 5)),
                () -> assertFalse(FormValidator.checkPassword("", 5)),
                () -> assertFalse(FormValidator.checkPassword(null, 5)));
    }
    @Test
    void passwordCheckerUppercaseTest() {
        assertAll(() -> assertFalse(FormValidator.checkPassword("1!aaa4", 5)),
                () -> assertTrue(FormValidator.checkPassword("1!aBB5", 5)));
    }
    @Test
    void passwordCheckerSpecialSymbolTest() {
        assertAll(() -> assertFalse(FormValidator.checkPassword("12aaL4", 5)),
                () -> assertTrue(FormValidator.checkPassword("1!ahW5", 5)));
    }

    //Phone Validator
    @Test
    void phoneValidatorAllNumbersTest() {
        assertAll(() -> assertFalse(FormValidator.checkPhoneNumber("")),
                () -> assertFalse(FormValidator.checkPhoneNumber(null)),
                () -> assertFalse(FormValidator.checkPhoneNumber("    ")),
                () -> assertFalse(FormValidator.checkPhoneNumber("86132321d4")),
                () -> assertTrue(FormValidator.checkPhoneNumber("863331233")));
    }
    @Test
    void phoneValidatorPrefixChangeTest() {
        assertAll(() -> assertEquals("+37060563633", FormValidator.changePrefix("860563633")),
                () -> assertEquals("760563633", FormValidator.changePrefix("760563633")),
                () -> assertNotEquals("868563633", FormValidator.changePrefix("868563633")));
    }
    @Test
    void phoneValidatorLengthByCountryTest() {
        assertAll(() -> assertFalse(FormValidator.checkPhoneNumberByCountry("LT", "+371605636111133")),
                () -> assertTrue(FormValidator.checkPhoneNumberByCountry("LT", "+3760356633")),
                () -> assertTrue(FormValidator.checkPhoneNumberByCountry("UK", "+447911123456")));
    }

    //Email Validator
    @Test
    void emailValidatorHasAddressSignTest() {
        assertAll(() -> assertFalse(FormValidator.checkEmail("mma.gmail.com")),
                () -> assertTrue(FormValidator.checkEmail("email@gmail.com")),
                () -> assertFalse(FormValidator.checkEmail("gmail.com")));
    }

    //accepted special chars ~!$%^&*_=+}{'?-.
    @Test
    void emailValidatorNotAllowedCharactersTest() {
        assertAll(() -> assertFalse(FormValidator.checkEmail("email£@gmail.com")),
                () -> assertTrue(FormValidator.checkEmail("em&&il@gmail.com")),
                () -> assertFalse(FormValidator.checkEmail("gma«il.com")));
    }
    @Test
    void emailValidatorDomainTest() {
        assertAll(() -> assertFalse(FormValidator.checkEmail("email@mail")),
                () -> assertTrue(FormValidator.checkEmail("email@mail#archive.com")),
                () -> assertFalse(FormValidator.checkEmail("email@mail..com")));
    }
    @Test
    void emailValidatorTopLevelDomainTest(){
        assertAll(() -> assertFalse(FormValidator.checkEmail("email@mail.aaccc")),
                () -> assertTrue(FormValidator.checkEmail("email@mail.bike")));
    }
}