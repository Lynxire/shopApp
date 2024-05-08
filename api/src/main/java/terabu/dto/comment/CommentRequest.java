package terabu.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class CommentRequest {
    private String comments;
    @NotNull
    private Long userId;
}
