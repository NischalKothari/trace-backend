package trace.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trace.dto.LoginRequest;
import trace.dto.LoginResponse;
import trace.dto.RegisterRequest;
import trace.dto.UserResponse;
import trace.entity.User;
import trace.repository.UserRepository;
import trace.service.interfaces.UserService;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse registerUser(RegisterRequest request) {

        if(userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email already exists");
        }
        if(userRepository.existsByUsername(request.username())){
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setEmail(request.email());
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail()
        );
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        User user = userRepository
                .findByEmail(request.email())
                .orElseThrow(() ->
                        new RuntimeException("No such email is registered with trace"));

        boolean match = passwordEncoder
                .matches(
                        request.password(),
                        user.getPassword()
                );

        if(!match){
            throw new RuntimeException("Invalid Credentials, try to trace back your password or hit forget password");
        }

        UserResponse response = new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );

        return new LoginResponse(response);
    }
}
