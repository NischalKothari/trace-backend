package trace.service.interfaces;

import trace.dto.LoginRequest;
import trace.dto.LoginResponse;
import trace.dto.RegisterRequest;
import trace.dto.UserResponse;

public interface UserService {
    UserResponse registerUser(RegisterRequest request);
    LoginResponse loginUser(LoginRequest request);
}
