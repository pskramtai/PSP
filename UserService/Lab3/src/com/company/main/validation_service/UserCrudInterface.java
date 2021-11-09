package com.company.main.validation_service;

import com.company.main.models.User;

import java.util.Optional;

public interface UserCrudInterface {
    public Optional<User> findById(int id);
    public void save(User user);
    public void deleteById(int id);
}
