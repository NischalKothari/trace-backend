package trace.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import trace.dto.TopTagResponse;
import trace.entity.Tag;
import trace.entity.User;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByNameAndUser(
      String name,
      User user
    );
    Long countByUser(User user);
    @Query("select new trace.dto.TopTagResponse(t.name,count(n)) from Tag t join t.notes n where t.user = :user group by t.id,t.name order by count(n) desc")
    List<TopTagResponse> findTopTags(
            @Param("user") User user,
            Pageable pageable
    );

}
