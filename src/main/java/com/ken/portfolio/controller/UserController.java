package com.ken.portfolio.controller;
import com.ken.portfolio.model.Response;
import com.ken.portfolio.model.User;
import com.ken.portfolio.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/keneth/api/v1")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAll(User user) {
        List<User> userList = userRepository.findAll();
        if (!userList.isEmpty()) {
            return ResponseEntity.ok(userList);
        } else {
            return ResponseEntity.ok(List.of());
        }
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Response> addUser(@Valid @RequestBody User user) {
        userRepository.save(user);
        log.info(String.format("User created: %s", user));
        Response response = new Response();
        response.setStatusCode("200");
        response.setMessage("User saved successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Response> deleteUser(@PathVariable Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            Response response = new Response();
            response.setStatusCode("200");
            response.setMessage("User successfully deleted");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
//    @PutMapping("update/{userId}")
    @PatchMapping("update/{userId}")
    public ResponseEntity<Response> updateUserById(@PathVariable Long userId, @Valid @RequestBody User updatedUser) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User existingUser = userOptional.get();
            // Update the existing user with the data from the updatedUser object
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
          existingUser.setOrganisation(updatedUser.getOrganisation());
          existingUser.setProfession(updatedUser.getProfession());
          existingUser.setPhone(updatedUser.getPhone());
          existingUser.setUpdatedAt(updatedUser.getUpdatedAt());
            existingUser.setUpdatedBy(updatedUser.getUpdatedBy());
            // Save the updated user
            userRepository.save(existingUser);

            Response response = new Response();
            response.setStatusCode("200");
            response.setMessage("User updated successfully");
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
