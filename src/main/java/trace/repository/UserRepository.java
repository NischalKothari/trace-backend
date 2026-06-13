package trace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trace.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}