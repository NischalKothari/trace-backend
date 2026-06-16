package trace.dto;

import java.time.LocalDateTime;
import java.util.Set;

public record NoteResponse(
        Long id,
        String title,
        String content,
        Set<String> tags,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
