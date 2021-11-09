package com.company.main.file_parsers;

import com.company.main.models.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonFileParser {
    private final String fileName;

    public JsonFileParser(String fileName){
        this.fileName = fileName;
    }

    public ArrayList<User> read(){
        try(var reader = new JsonReader(new FileReader(fileName))){
            Gson gson = new Gson();
            Type userArrayType = new TypeToken<ArrayList<User>>(){}.getType();
            return gson.fromJson(reader, userArrayType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean write(ArrayList<User> users){

        try(var writer = new FileWriter(fileName)){
            Gson gson = new Gson();
            gson.toJson(users, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
