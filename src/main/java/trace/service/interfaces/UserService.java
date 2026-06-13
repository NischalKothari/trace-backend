package trace.service.interfaces;

import trace.dto.RegisterRequest;
import trace.dto.UserResponse;

public interface UserService {
    UserResponse registerUser(RegisterRequest request);
}
