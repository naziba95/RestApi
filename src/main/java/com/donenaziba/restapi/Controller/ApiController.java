package com.donenaziba.restapi.Controller;

import com.donenaziba.restapi.Model.User;
import com.donenaziba.restapi.Repo.UserRepo;
import com.donenaziba.restapi.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String GetPage(){
        return "Welcome";
    }

    // Create a new user
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        User userCreated = userRepo.save(user);
        ApiResponse<User> response = new ApiResponse<>("00", "success", userCreated);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Read all users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    // Read user by ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepo.findById(id);
    }

    // Update an existing user
    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setAge(userDetails.getAge());
        user.setOccupation(userDetails.getOccupation());
        return userRepo.save(user);
    }

    // Delete a user by ID
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id " + id));
        userRepo.delete(user);
        return "User deleted with id " + id;
    }
}
