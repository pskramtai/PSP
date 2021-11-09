package com.company.main.validation_service;

import com.company.main.file_parsers.JsonFileParser;
import com.company.main.models.User;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;


public class UserCrudFile implements UserCrudInterface {
    private final JsonFileParser jsonFileParser;

    public UserCrudFile(JsonFileParser jsonFileParser){
        this.jsonFileParser = jsonFileParser;
    }

    @Override
    public Optional<User> findById(int id) {
        var users = jsonFileParser.read();
        var user = users.stream().filter(u -> u.getUserID() == id).findFirst();
        return user;
    }

    @Override
    public void save(User user) {
        var users = jsonFileParser.read();

        if(users==null){
            users = new ArrayList<User>();
        }

        if(user.getUserID() != 0){

           var userExists = users.stream().anyMatch(u -> u.getUserID() == user.getUserID());
           if(!userExists){
               return;
           }

           users.replaceAll(u -> u.getUserID() == user.getUserID() ? user : u);

        }
        else{
            if(users == null){
                user.setUserID(1);
            }
            else {
                int newId = 0;

                for (int i = 0; i < users.size(); i++) {
                    if (users.get(i).getUserID() > newId) {
                        newId = users.get(i).getUserID();
                    }
                }

                user.setUserID(newId + 1);
            }
            users.add(user);
        }
        jsonFileParser.write(users);
    }

    @Override
    public void deleteById(int id) {
        var users = jsonFileParser.read();
        users = users.stream().filter(u -> u.getUserID() != id).collect(Collectors.toCollection(ArrayList::new));
        jsonFileParser.write(users);
    }
}
