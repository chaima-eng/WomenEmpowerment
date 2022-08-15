package com.example.backend.Controller;

import com.example.backend.Entity.User;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Service.IntUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController("/crud")
@CrossOrigin("*")

public class CrudController {

    @Autowired
    IntUserService userService;




    @GetMapping("/getAll")
    List<User> getAll()
    {
        return userService.getAll();
    }




    @PutMapping("/update/{id}")
    ResponseEntity<User> update(@PathVariable("id") int id,@RequestBody User user)
    {
        return userService.update(id,user);
    }

    @DeleteMapping("/delete/{id}")
    Map<String, Boolean> Delete(@PathVariable("id") int ID)
            throws ResourceNotFoundException
    {

        return userService.Delete(ID);
    }

    @GetMapping("/getByID/{id}")
    ResponseEntity<User> getById(@PathVariable("id") int Id)  throws ResourceNotFoundException
    {

        return userService.getById(Id);

    }






}
