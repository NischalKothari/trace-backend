package trace.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import trace.dto.RegisterRequest;
import trace.dto.UserResponse;
import trace.entity.User;
import trace.repository.UserRepository;
import trace.service.interfaces.UserService;

import java.time.LocalDateTime;

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
}
