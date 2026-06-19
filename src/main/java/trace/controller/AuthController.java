package trace.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trace.dto.LoginRequest;
import trace.dto.LoginResponse;
import trace.dto.RegisterRequest;
import trace.dto.UserResponse;
import trace.service.interfaces.JwtService;
import trace.service.interfaces.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid RegisterRequest request){
        UserResponse response =  userService.registerUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        LoginResponse response =userService.loginUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");
        return jwtService.extractEmail(token);
    }

    @GetMapping("/validate")
    public boolean validate(@RequestHeader("Authorization") String token){
        token = token.replace("Bearer ", "");
        return jwtService.validateToken(token);
    }


}
