package com.example.webportal.services;

import com.example.webportal.DTO.UserLoginDTO;
import com.example.webportal.DTO.UserRegistrationDTO;
import com.example.webportal.exceptions.DuplicateEmailException;
import com.example.webportal.models.User;
import com.example.webportal.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;



    public void registerUser(UserRegistrationDTO registrationDTO) {
        validateRegistrationData(registrationDTO);

        String encodedPassword = passwordEncoder.encode(registrationDTO.getPassword());

        User user = new User(registrationDTO.getEmail(), encodedPassword, registrationDTO.getFirstName(), registrationDTO.getLastName());

        userRepository.save(user);
    }

    boolean validateRegistrationData(UserRegistrationDTO registrationDTO) {
        if (!isValidEmail(registrationDTO.getEmail())) {
            throw new IllegalArgumentException("Invalid email address");
        }

        if (userRepository.existsByEmail(registrationDTO.getEmail())) {
            throw new DuplicateEmailException("Email address is already in use");
        }
        return true;
    }

    boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }



    public boolean validateLoginData(UserLoginDTO loginDTO) {
        User user = getUserByEmail(loginDTO.getEmail());

        if (loginDTO.getEmail() == null || loginDTO.getPassword() == null) {
            throw new IllegalArgumentException("Invalid login data");
        }

        return passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }

    public String generateJwtToken(String username) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key)
                .compact();

        logger.info("Generated JWT Token: {}", token);

        return token;
    }


}
