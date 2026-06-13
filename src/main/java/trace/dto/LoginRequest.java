package trace.dto;

public record LoginRequest(
        String email,
        String password
){}
