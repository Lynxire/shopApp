package terabu.dto.comment;

import lombok.Data;

@Data
public class CommentRequest {
    private String comments;
    private Long userId;
}
