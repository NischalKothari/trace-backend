package trace.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import trace.entity.Note;
import trace.entity.User;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note,Long> {

    List<Note> findByUser(User user);
    Optional<Note> findByIdAndUser(Long id,User user);

}