package com.example.workspaceconsole.serviceTest;

import com.example.workspaceconsole.domain.User;
import com.example.workspaceconsole.dto.UserDTO;
import com.example.workspaceconsole.repository.UserRepository;
import com.example.workspaceconsole.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService userService;

    private UserDTO userDTO;
    private User user;

    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setPersonalNo("1234567890");
        userDTO.setFullName("John Doe");
        userDTO.setEmail("john@example.com");
        userDTO.setPhoneNumber("1234567890");
        userDTO.setAddress("123 Street, City");

        user = new User();
        user.setId(1);
        user.setPersonalNo("1234567890");
        user.setFullName("John Doe");
        user.setEmail("john@example.com");
        user.setPhoneNumber("1234567890");
        user.setAddress("123 Street, City");
    }

    @Test
    public void testGetAllUser() {
        when(repository.findAll()).thenReturn(Collections.singletonList(user));
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        Set<UserDTO> result = userService.getAllUser();

        assertEquals(1, result.size());
        assertTrue(result.contains(userDTO));
    }

    @Test
    public void testSaveUser() {
        when(modelMapper.map(userDTO, User.class)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.saveUser(userDTO);

        assertEquals(userDTO, result);
    }

    @Test
    public void testUpdateUser() {
        long id = 1;
        when(repository.existsById(id)).thenReturn(true);
        when(modelMapper.map(userDTO, User.class)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);

        UserDTO result = userService.updateUser(id, userDTO);

        assertEquals(userDTO, result);
    }

    @Test
    public void testUpdateUserNotFound() {
        long id = 1;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.updateUser(id, userDTO));
    }

    @Test
    public void testDeleteUserById() {
        long id = 1;
        when(repository.existsById(id)).thenReturn(true);

        userService.deleteUserById(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testDeleteUserByIdNotFound() {
        long id = 1;
        when(repository.existsById(id)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userService.deleteUserById(id));
    }
}
