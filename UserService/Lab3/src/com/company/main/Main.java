package com.company.main;

import com.company.main.file_parsers.JsonFileParser;
import com.company.main.validation_service.UserCrudFile;
import com.company.main.validation_service.UserCrudInterface;
import com.company.main.validation_service.UserValidator;
import com.company.main.validation_service.UserValidatorInterface;
import com.company.main.models.User;
import com.company.main.validators.EmailValidator;
import com.company.main.validators.PasswordChecker;
import com.company.main.validators.PhoneValidator;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {

        UserValidatorInterface userValidatorInterface = new UserValidator(new EmailValidator(), new PasswordChecker(), new PhoneValidator());
        UserCrudInterface userCrudInterface = new UserCrudFile(new JsonFileParser("users.json"));
        Scanner sc = new Scanner(System.in);
        User user;

        String userChoice;

        System.out.println("1 - Add user\n2 - Edit user\n3 - Delete User");
        userChoice = sc.nextLine();


        switch (userChoice) {
            case "1" -> {
                user = generateUser(sc);
                if (userValidatorInterface.isValid(user))
                    userCrudInterface.save(user);
            }
            case "2" -> {
                System.out.println("Enter user ID");
                int userID = Integer.parseInt(sc.nextLine());
                user = generateUser(sc);
                user.setUserID(userID);
                if (userValidatorInterface.isValid(user))
                    userCrudInterface.save(user);
            }
            case "3" -> {
                System.out.println("Enter user ID");
                userCrudInterface.deleteById(Integer.parseInt(sc.nextLine()));
            }
            default -> System.out.println("Unexpected option");
        }
    }

    private static User generateUser(Scanner sc){
        User user = new User();

        System.out.println("Enter name");
        user.setName(sc.nextLine());
        System.out.println("Enter surname");
        user.setSurname(sc.nextLine());
        System.out.println("Enter phone number");
        user.setPhoneNumber(sc.nextLine());
        System.out.println("Enter email");
        user.setEmail(sc.nextLine());
        System.out.println("Enter address");
        user.setAddress(sc.nextLine());
        System.out.println("Enter password");
        user.setPassword(sc.nextLine());

        return user;
    }
}
