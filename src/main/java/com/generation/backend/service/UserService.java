package com.generation.backend.service;


import com.generation.backend.model.Users;

import java.util.List;
import java.util.Map;

public interface UserService {
    
    List<Users> getAllUsers();
    
    Users createUser(Users user);
    
    Users getUserById(Long id);
    
    Users getUserByName(String name);
    
    Users updateUser(Users user);
    
    Map<String,String> deleteUserById(Long id);
    
}
