package com.example.webportal.services;

import com.example.webportal.DTO.UserLoginDTO;
import com.example.webportal.DTO.UserRegistrationDTO;
import com.example.webportal.exceptions.DuplicateEmailException;
import com.example.webportal.models.User;
import com.example.webportal.repositories.UserRepository;
import com.example.webportal.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testRegisterUser_ValidRegistrationData_UserSaved() {
        // Given
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO("test@example.com", "password", "John", "Doe");
        when(userRepository.existsByEmail(registrationDTO.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(registrationDTO.getPassword())).thenReturn("encodedPassword");

        // When
        userService.registerUser(registrationDTO);

        // Then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_DuplicateEmail_ThrowsDuplicateEmailException() {
        // Given
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO("existing@example.com", "password", "John", "Doe");
        when(userRepository.existsByEmail(registrationDTO.getEmail())).thenReturn(true);

        // When, Then
        assertThrows(DuplicateEmailException.class, () -> userService.registerUser(registrationDTO));
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testValidateLoginData_ValidLoginData_ReturnsTrue() {
        // Given
        UserLoginDTO loginDTO = new UserLoginDTO("test@example.com", "password");
        User user = new User("test@example.com", "encodedPassword", "John", "Doe");
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(true);

        // When
        boolean result = userService.validateLoginData(loginDTO);

        // Then
        assertTrue(result);
    }

    @Test
    void testValidateLoginData_InvalidLoginData_ThrowsIllegalArgumentException() {
        // Given
        UserLoginDTO loginDTO = new UserLoginDTO(null, null);

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> userService.validateLoginData(loginDTO));
        verify(userRepository, never()).findByEmail(anyString());
        verify(passwordEncoder, never()).matches(anyString(), anyString());
    }

    @Test
    void testGetUserByEmail() {
        // Given
        String userEmail = "test@example.com";
        User user = new User(userEmail, "encodedPassword", "John", "Doe");
        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(user));

        // When
        User result = userService.getUserByEmail(userEmail);

        // Then
        assertNotNull(result);
        assertEquals(userEmail, result.getEmail());
    }

    @Test
    void testGetUserByEmailWithInvalidEmail() {
        // Given
        String userEmail = null;

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> userService.getUserByEmail(userEmail));
        verify(userRepository, never()).findByEmail(anyString());
    }

    @Test
    void testIsValidEmail_ValidEmail_ReturnsTrue() {
        // When
        boolean result = userService.isValidEmail("test@example.com");

        // Then
        assertTrue(result);
    }

    @Test
    void testIsValidEmail_InvalidEmail_ReturnsFalse() {
        // When
        boolean result = userService.isValidEmail("invalid-email");

        // Then
        assertFalse(result);
    }
}
