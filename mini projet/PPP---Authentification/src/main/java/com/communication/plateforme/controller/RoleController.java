package com.communication.plateforme.controller;

import com.communication.plateforme.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = {"Authorization", "Content-Type"})
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @GetMapping("/list-role")
    public ResponseEntity listUsers() {

        return new ResponseEntity<>(roleService.listRole(), HttpStatus.OK);
    }
}
