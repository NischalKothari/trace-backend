package trace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trace.entity.Tag;
import trace.entity.User;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByNameAndUser(
      String name,
      User user
    );
}
