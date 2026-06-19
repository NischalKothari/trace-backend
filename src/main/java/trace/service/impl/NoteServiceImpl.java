package trace.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trace.dto.CreateNoteRequest;
import trace.dto.NoteResponse;
import trace.entity.Note;
import trace.entity.Tag;
import trace.entity.User;
import trace.exception.ResourceNotFoundException;
import trace.repository.NoteRepository;
import trace.service.interfaces.NoteService;
import trace.service.interfaces.TagService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService{

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
    public Page<NoteResponse> getMyNotes(User user, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        Page<Note> notePage = noteRepository.findByUser(user,pageable);
        return notePage.map(this::mapToResponse);
    }

    @Override
    public NoteResponse getNoteById(Long id, User user) {
        Note note = noteRepository.findByIdAndUser(id,user).orElseThrow(() -> new ResourceNotFoundException("Note not found"));
        return mapToResponse(note);
    }

    @Override
    @Transactional
    public NoteResponse updateNote(Long id, CreateNoteRequest request, User user) {
        Note note = noteRepository.findByIdAndUser(id,user).orElseThrow(() -> new ResourceNotFoundException("Note not found"));

        note.setTitle(request.title());
        note.setContent(request.content());

        return mapToResponse(note);
    }

    @Override
    public String deleteNote(Long id, User user) {
        Note note = noteRepository.findByIdAndUser(id,user).orElseThrow(()->new ResourceNotFoundException("Note is not traceable"));
        noteRepository.delete(note);
        return "Note deleted successfully";
    }

    @Override
    public List<NoteResponse> searchNotes(User user, String keyword) {
        List<Note> noteList = noteRepository.searchNotes(user, keyword);
        return mapToResponseList(noteList);
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

    private List<NoteResponse> mapToResponseList(List<Note> noteList){

        return noteList.stream()
                .map(this::mapToResponse)
                .toList();

    }

}
