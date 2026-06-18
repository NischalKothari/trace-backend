package trace.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import trace.dto.DashboardResponse;
import trace.dto.RecentNoteResponse;
import trace.dto.TopTagResponse;
import trace.entity.Note;
import trace.entity.User;
import trace.repository.NoteRepository;
import trace.repository.TagRepository;
import trace.service.interfaces.DashboardService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DashboardServiceImpl implements DashboardService{
    private final NoteRepository noteRepository;
    private final TagRepository tagRepository;
    @Override
    public DashboardResponse getDashboard(User user) {

        Long totalNotes = noteRepository.countByUser(user);

        Long totalTags = tagRepository.countByUser(user);

        LocalDateTime startOfWeek = LocalDate.now()
                .with(DayOfWeek.MONDAY)
                .atStartOfDay();
        Long notesThisWeek = noteRepository.countByUserAndCreatedAtGreaterThanEqual(user, startOfWeek);

        List<RecentNoteResponse> recentNotes = noteRepository
                .findTop5ByUserOrderByUpdatedAtDesc(user)
                .stream()
                .map(this::mapToRecentNotes)
                .toList();

        Pageable pageable = PageRequest.of(0,5);
        List<TopTagResponse> topTagResponses = tagRepository.findTopTags(user, pageable);

        return new DashboardResponse(
                totalNotes,
                totalTags,
                notesThisWeek,
                recentNotes,
                topTagResponses
        );

    }

    private RecentNoteResponse mapToRecentNotes(Note note){
        return new RecentNoteResponse(
                note.getId(),
                note.getTitle(),
                note.getUpdatedAt()
                );
    }
}
