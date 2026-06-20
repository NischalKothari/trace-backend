package trace.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import trace.dto.NoteResponse;
import trace.entity.Note;
import trace.entity.User;
import trace.exception.ResourceNotFoundException;
import trace.repository.NoteRepository;
import trace.repository.TagRepository;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NoteServiceImplTest {
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private TagRepository tagRepository;
    @InjectMocks
    private NoteServiceImpl noteService;

    @Test
    void shouldReturnNoteWhenExists(){

        //Arrange{

        User user = new User(); // create a user instance

        Note note = new Note(); // create a note instance

        // mock all places where you cant have a blank and whats necessary to be passed
        note.setId(1L); // make a mock id
        note.setTitle("Shayri"); // mock title
        note.setContent("Aaj kamainga to kal ghar baith ke khainga"); // mock content
        note.setTags(new HashSet<>());

        //when noteRepo finds this thing then pretend it finds smthg and return that thing
        when(noteRepository.findByIdAndUser(1L, user)).thenReturn(Optional.of(note));

        //}

        //Act{

        //get the content which will be returned
        NoteResponse noteResponse = noteService.getNoteById(1L,user);

        //}

        //Assert{

        assertEquals(1L,noteResponse.id()); // check if expected id is returned by dto
        assertEquals("Shayri",noteResponse.title()); // check if expected title is returned
        verify(noteRepository).findByIdAndUser(1L,user); // did service actually call note repo

        //}
    }

    @Test
    void shouldNotReturnNoteWhenDoesNotExist(){

        User user = new User();

        when(noteRepository.findByIdAndUser(1L, user)).thenReturn(Optional.empty());

        ResourceNotFoundException ex =
                assertThrows( // assert throws for exception
                        ResourceNotFoundException.class,
                        () -> noteService.getNoteById(1L,user)
                );

        assertEquals(
                "Note not found",
                ex.getMessage()
        );

        verify(noteRepository)
                .findByIdAndUser(1L,user);

    }

}
