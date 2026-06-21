package trace.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import trace.entity.Note;
import trace.entity.User;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void shouldFindNoteByIdAndUser(){

        //Arrange

        User user = new User();
        user.setEmail("nick@yahoo.com");
        user.setUsername("nick");
        user.setPassword("trustMeItsAStrongPassword");
        entityManager.persist(user);

        Note note = new Note();
        note.setTitle("ABC");
        note.setContent("DEFGHI");
        note.setTags(new HashSet<>());
        note.setUser(user);
        entityManager.persist(note);

        entityManager.flush();

        //Act

        Optional<Note> result = noteRepository.findByIdAndUser(note.getId(), user);

        //Assert

        assertTrue(result.isPresent());
        assertEquals(
                note.getId(),
                result.get().getId()
        );

    }
}
