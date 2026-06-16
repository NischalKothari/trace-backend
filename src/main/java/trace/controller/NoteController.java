package trace.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import trace.dto.CreateNoteRequest;
import trace.dto.NoteResponse;
import trace.entity.User;
import trace.service.interfaces.NoteService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping
    public NoteResponse createNote(
            @Valid @RequestBody CreateNoteRequest request,
            @AuthenticationPrincipal User user){
        return noteService.createNote(request,user);
    }

    @GetMapping
    public List<NoteResponse> getMyNotes(
            @AuthenticationPrincipal User user
    ){
        return noteService.getMyNotes(user);
    }

    @GetMapping("/{id}")
    public NoteResponse getNoteById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ){
        return noteService.getNoteById(id,user);
    }

    @PutMapping("/{id}")
    public NoteResponse updateNoteById(
            @PathVariable Long id,
            @Valid @RequestBody CreateNoteRequest request,
            @AuthenticationPrincipal User user
    ){
        return noteService.updateNote(id,request,user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNoteById(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ){
        try {
            String s = noteService.deleteNote(id,user);
            return ResponseEntity.ok(s);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Untraceable");
        }
    }
}
