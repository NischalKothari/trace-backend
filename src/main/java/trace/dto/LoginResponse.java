package trace.dto;

public record LoginResponse(
        String token,
        UserResponse user
){}
