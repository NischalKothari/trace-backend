package trace.dto;

import java.time.LocalDateTime;

public record RecentNoteResponse(
        Long id,
        String title,
        LocalDateTime updatedAt
) {}
