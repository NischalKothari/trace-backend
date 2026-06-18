package trace.dto;

import java.util.List;

public record DashboardResponse(
        Long totalNotes,
        Long totalTags,
        Long notesThisWeek,
        List<RecentNoteResponse> recentNotes,
        List<TopTagResponse> topTags
) {}
