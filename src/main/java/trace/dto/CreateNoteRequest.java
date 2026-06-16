package trace.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateNoteRequest(
        @NotBlank(message = "We have to trace a title, enter it")
        String title,
        String content
) {}
