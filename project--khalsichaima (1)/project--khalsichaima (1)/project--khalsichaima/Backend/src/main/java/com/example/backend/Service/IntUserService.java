package com.example.backend.Service;

import com.example.backend.Entity.User;
import com.example.backend.Exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IntUserService {


    List<User> getAll();

    ResponseEntity<User> update(int id, User user);

    Map<String, Boolean> Delete(int Id)
            throws ResourceNotFoundException;



    ResponseEntity<User> getById(int Id)  throws ResourceNotFoundException;




}
