package trace.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trace.entity.Note;
import trace.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note,Long> {

    Page<Note> findByUser(User user, Pageable pageable);
    Optional<Note> findByIdAndUser(Long id,User user);
    @Query("Select n from Note n where n.user = :user and(lower(n.title) like lower(concat('%', :keyword , '%')) or lower(n.content) like lower(concat('%', :keyword , '%')))")
    List<Note> searchNotes(
            @Param("user") User user,
            @Param("keyword") String keyword
    );
    Long countByUser(User user);
    Long countByUserAndCreatedAtGreaterThanEqual(
            User user,
            LocalDateTime startOfWeek
    );
    List<Note> findTop5ByUserOrderByUpdatedAtDesc(
            User user
    );

}