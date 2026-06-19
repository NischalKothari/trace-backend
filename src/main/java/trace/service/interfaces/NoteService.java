package trace.service.interfaces;

import org.springframework.data.domain.Page;
import trace.dto.CreateNoteRequest;
import trace.dto.NoteResponse;
import trace.entity.User;

import java.util.List;

public interface NoteService {

    NoteResponse createNote(CreateNoteRequest request, User user);
    Page<NoteResponse> getMyNotes(User user,int pageNumber,int pageSize);
    NoteResponse getNoteById(Long id, User user);
    NoteResponse updateNote(Long id, CreateNoteRequest request, User user);
    String deleteNote(Long id, User user);
    List<NoteResponse> searchNotes(User user, String keyword);
}
