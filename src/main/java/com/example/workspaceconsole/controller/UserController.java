package com.example.workspaceconsole.controller;

import com.example.workspaceconsole.dto.UserDTO;
import com.example.workspaceconsole.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Get all users")
    @GetMapping
    public ResponseEntity<Set<UserDTO>> getAllUsers() {
        Set<UserDTO> users = service.getAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO savedUser = service.saveUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long id,@Valid  @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = service.updateUser(id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(summary = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found"),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable long id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
