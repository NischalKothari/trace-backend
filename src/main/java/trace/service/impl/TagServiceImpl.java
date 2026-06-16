package trace.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trace.entity.Tag;
import trace.entity.User;
import trace.repository.TagRepository;
import trace.service.interfaces.TagService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{
    private final TagRepository tagRepository;
    @Override
    public Set<Tag> getOrCreateTags(Set<String> tagNames, User user) {
        if(tagNames == null || tagNames.isEmpty()){
            return new HashSet<>();
        }
        Set<Tag> tags = tagNames
                .stream()
                .map(tagString -> {
                    String finalTagString = tagString.trim().toLowerCase();
                    return tagRepository
                            .findByNameAndUser(
                                    finalTagString,
                                    user
                            )
                            .orElseGet(() -> {
                        Tag tag = new Tag();
                        tag.setName(finalTagString);
                        tag.setUser(user);
                        return tagRepository.save(tag);
                    });
                })
                .collect(Collectors.toSet());
        return tags;
    }
}
