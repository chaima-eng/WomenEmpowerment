package com.example.backend.Service;

import com.example.backend.Entity.User;
import com.example.backend.Exception.ResourceNotFoundException;
import com.example.backend.Repository.IntUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CrudUserService implements IntUserService {


    @Autowired
    IntUserRepo ur;



    @Override
    public List<User> getAll() {
        return ur
                .findAll();
    }

    @Override
    public ResponseEntity<User> update(int id, User user) {
        User c = ur.getById(id);
        c.setFirstName(user.getFirstName());
        c.setLastName(user.getLastName());
        c.setAdresse(user.getAdresse());
        c.setCin(user.getCin());
        c.setCivilState(user.getCivilState());
        c.setDateOfBirth(user.getDateOfBirth());
        c.setEmail(user.getEmail());
        c.setJob(user.getJob());

        c.setMonthlyUncome(user.getMonthlyUncome());
        c.setNbrOffamilly(user.getNbrOffamilly());
        c.setPostalcode(user.getPostalcode());
        c.setStudyLevel(user.getStudyLevel());

        c.setTel(user.getTel());
        c.setUserName(user.getUserName());

        User update = ur.save(c);
        return ResponseEntity.ok().body(update);
    }













    @Override
    public Map<String, Boolean> Delete(int userId) throws ResourceNotFoundException {
        User user = ur.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(" not found   :: " + userId));
        ur.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @Override
    public ResponseEntity<User> getById(int Id)
          throws ResourceNotFoundException {
            User user = ur.findById(Id)
                    .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + Id));
            return ResponseEntity.ok().body(user);
    }







}
