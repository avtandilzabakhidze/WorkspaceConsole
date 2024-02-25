package com.example.workspaceconsole.service;

import com.example.workspaceconsole.domain.User;
import com.example.workspaceconsole.dto.UserDTO;
import com.example.workspaceconsole.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Set<UserDTO> getAllUser() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toSet());
    }

    public UserDTO saveUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        return modelMapper.map(user, UserDTO.class);
    }

    public UserDTO updateUser(long id,UserDTO userDTO){
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        userDTO.setId(id);
        User user = modelMapper.map(userDTO, User.class);
        return modelMapper.map(user, UserDTO.class);
    }

    public void deleteUserById(long id){
        repository.deleteById(id);
    }
}
