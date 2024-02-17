package com.example.webportal.controllers;

import com.example.webportal.DTO.UserLoginDTO;
import com.example.webportal.DTO.UserRegistrationDTO;
import com.example.webportal.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        // Given
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO(/* provide necessary details */);

        // When
        ResponseEntity<String> response = userController.registerUser(registrationDTO);

        // Then
        verify(userService, times(1)).registerUser(registrationDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Registration successful", response.getBody());
    }

    @Test
    void testLoginUser_Success() {
        // Given
        UserLoginDTO loginDTO = new UserLoginDTO(/* provide necessary details */);
        when(userService.validateLoginData(loginDTO)).thenReturn(true);
        when(userService.generateJwtToken(loginDTO.getEmail())).thenReturn("dummyToken");

        // When
        ResponseEntity<?> response = userController.loginUser(loginDTO);

        // Then
        verify(userService, times(1)).validateLoginData(loginDTO);
        verify(userService, times(1)).generateJwtToken(loginDTO.getEmail());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("token", "dummyToken"), response.getBody());
    }

    @Test
    void testLoginUser_InvalidPassword() {
        // Given
        UserLoginDTO loginDTO = new UserLoginDTO(/* provide necessary details */);
        when(userService.validateLoginData(loginDTO)).thenReturn(false);

        // When
        ResponseEntity<?> response = userController.loginUser(loginDTO);

        // Then
        verify(userService, times(1)).validateLoginData(loginDTO);
        verify(userService, never()).generateJwtToken(anyString());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Collections.singletonMap("error", "Invalid password"), response.getBody());
    }
}
