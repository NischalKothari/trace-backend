package trace.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record CreateNoteRequest(
        @NotBlank(message = "We have to trace a title, enter it")
        String title,
        String content,
        Set<String> tags
) {}
