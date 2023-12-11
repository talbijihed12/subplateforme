package com.communication.plateforme.controller;

import com.communication.plateforme.model.User;
import com.communication.plateforme.services.IUserService;
import com.communication.plateforme.utils.exceptions.SpringPlateformeException;
import com.communication.plateforme.utils.payload.request.UpdateUserRequest;
import com.communication.plateforme.utils.payload.response.MessageResponse;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Authorization", "Content-Type"})
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    IUserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/listUser")
    public ResponseEntity listUsers() {

        return new ResponseEntity<>(userService.listUser(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }



    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        this.userService.updateUser(updateUserRequest);
        return ResponseEntity.ok(new MessageResponse("User Updated successfully!"));
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.findUserByUsername(username);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (SpringPlateformeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
