package trace.dto;

public record TopTagResponse(
        String tagName,
        Long usageCount
) {
}
