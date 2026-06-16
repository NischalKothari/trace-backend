package trace.service.interfaces;

import trace.dto.CreateNoteRequest;
import trace.dto.NoteResponse;
import trace.entity.User;

import java.util.List;

public interface NoteService {

    NoteResponse createNote(CreateNoteRequest request, User user);
    List<NoteResponse> getMyNotes(User user);
    NoteResponse getNoteById(Long id, User user);
    NoteResponse updateNote(Long id, CreateNoteRequest request, User user);
    String deleteNote(Long id, User user);

}
