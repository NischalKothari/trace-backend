package trace.service.interfaces;

import trace.entity.Tag;
import trace.entity.User;

import java.util.Set;

public interface TagService {

    Set<Tag> getOrCreateTags(
            Set<String> tagNames,
            User user
    );

}