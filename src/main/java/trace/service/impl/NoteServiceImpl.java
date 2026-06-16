package trace.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trace.dto.CreateNoteRequest;
import trace.dto.NoteResponse;
import trace.entity.Note;
import trace.entity.Tag;
import trace.entity.User;
import trace.repository.NoteRepository;
import trace.repository.TagRepository;
import trace.service.interfaces.NoteService;
import trace.service.interfaces.TagService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final TagService tagService;

    @Override
    public NoteResponse createNote(CreateNoteRequest request, User user) {
        Note note = new Note();
        Set<Tag> tags = tagService.getOrCreateTags(request.tags(),user);
        note.setTitle(request.title());
        note.setContent(request.content());
        note.setUser(user);
        note.setTags(tags);
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

        Set<String> tagNames = note.getTags()
                .stream()
                .map(Tag::getName)
                .collect(Collectors.toSet());

        return new NoteResponse(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                tagNames,
                note.getCreatedAt(),
                note.getUpdatedAt()
        );
    }

}
