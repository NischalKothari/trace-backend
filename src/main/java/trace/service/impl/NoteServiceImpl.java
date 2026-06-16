package trace.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trace.dto.CreateNoteRequest;
import trace.dto.NoteResponse;
import trace.entity.Note;
import trace.entity.User;
import trace.repository.NoteRepository;
import trace.service.interfaces.NoteService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    @Override
    public NoteResponse createNote(CreateNoteRequest request, User user) {
        Note note = new Note();
        note.setTitle(request.title());
        note.setContent(request.content());
        note.setUser(user);
        Note savedNote = noteRepository.save(note);
        return mapToResponse(savedNote);
    }

    @Override
    public List<NoteResponse> getMyNotes(User user) {
        List<Note> noteList = noteRepository.findByUser(user);
        return noteList.stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public NoteResponse getNoteById(Long id, User user) {
        Note note = noteRepository.findByIdAndUser(id,user).orElseThrow(() -> new RuntimeException("Note not found"));
        return mapToResponse(note);
    }

    @Override
    public NoteResponse updateNote(Long id, CreateNoteRequest request, User user) {
        Note note = noteRepository.findByIdAndUser(id,user).orElseThrow(() -> new RuntimeException("Note not found"));

        note.setTitle(request.title());
        note.setContent(request.content());

        noteRepository.save(note);

        return mapToResponse(note);
    }

    @Override
    public String deleteNote(Long id, User user) {
        Note note = noteRepository.findByIdAndUser(id,user).orElseThrow(()->new RuntimeException("Note is not traceable"));
        noteRepository.delete(note);
        return "Note deleted successfully";
    }

    private NoteResponse mapToResponse(Note note) {
        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }

}
