package com.example.workspaceconsole.controller;

import com.example.workspaceconsole.dto.UserDTO;
import com.example.workspaceconsole.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public Set<UserDTO> getAllUser() {
        return service.getAllUser();
    }

    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return service.saveUser(userDTO);
    }

    @PutMapping("{id}")
    public UserDTO updateUser(@PathVariable long id, @RequestBody UserDTO userDTO) {
        return service.updateUser(id, userDTO);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable long id) {
        service.deleteUserById(id);
    }
}
