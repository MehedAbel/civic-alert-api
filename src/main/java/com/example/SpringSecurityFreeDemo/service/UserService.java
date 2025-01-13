package com.example.SpringSecurityFreeDemo.service;

import com.example.SpringSecurityFreeDemo.dto.LoginDto;
import com.example.SpringSecurityFreeDemo.dto.LoginResponseDto;
import com.example.SpringSecurityFreeDemo.dto.RegisterDto;
import com.example.SpringSecurityFreeDemo.dto.RegisterResponseDto;
import com.example.SpringSecurityFreeDemo.exception.InvalidLoginCredentialsException;
import com.example.SpringSecurityFreeDemo.exception.InvalidRegisterCredentialsException;
import com.example.SpringSecurityFreeDemo.exception.UserAlreadyExistsException;
import com.example.SpringSecurityFreeDemo.model.Role;
import com.example.SpringSecurityFreeDemo.model.Users;
import com.example.SpringSecurityFreeDemo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Ref;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private JWTService jwtService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    AuthenticationManager authManager;

    // REGISTER SERVICE
    public RegisterResponseDto register(RegisterDto dto) {
        validateRegisterRequest(dto);

        Users user = mapRegisterDtoToUser(dto);
        user.setPassword(encoder.encode(user.getPassword()));

        return mapToRegisterResponseDto(repo.save(user));
    }

    private void validateRegisterRequest(RegisterDto dto) {
        String nameRegex = "^[a-zA-Zà-žÀ-Ž-]{2,50}$";
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$";

        if (!(
                dto.getFirstName().matches(nameRegex) &&
                dto.getLastName().matches(nameRegex) &&
                dto.getEmail().matches(emailRegex) &&
                dto.getPassword().matches(passwordRegex)))
        {
            throw new InvalidRegisterCredentialsException("Invalid user registration credentials format");
        }

        if (repo.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email already in use by another user");
        }
    }

    // LOGIN SERVICE
    public LoginResponseDto login(LoginDto loginUser) {
        Users user = validateLoginRequest(loginUser);

        LoginResponseDto loginResponseDto = mapToLoginResponseDto(user, jwtService.generateToken(user.getUsername(), user.getRoles()));

        return loginResponseDto;
    }

    private Users validateLoginRequest(LoginDto loginDto) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,}$";

        if (!(loginDto.getEmail().matches(emailRegex) && loginDto.getPassword().matches(passwordRegex))) {
            throw new InvalidLoginCredentialsException("Invalid email or password");
        }

        Users user = repo.findByEmail(loginDto.getEmail());

        if (user == null) {
            throw new InvalidLoginCredentialsException("Invalid email or password");
        }

        try {
            Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (Exception e) {
            throw new InvalidLoginCredentialsException("Invalid email or password");
        }

        return user;
    }

    private RegisterResponseDto mapToRegisterResponseDto(Users user) {
        RegisterResponseDto dto = new RegisterResponseDto();
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setRoles(user.getRoles());

        return dto;
    }

    private Users mapRegisterDtoToUser(RegisterDto dto) {
        Users user = new Users();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRoles(Set.of(Role.USER));

        return user;
    }

    private LoginResponseDto mapToLoginResponseDto(Users user, String accessToken) {
        LoginResponseDto dto = new LoginResponseDto();

        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setAccessToken(accessToken);
        dto.setRoles(user.getRoles());

        return dto;
    }
}
