package terabu.dto.comment;

import lombok.Data;

@Data
public class CommentResponse {
    private String email;
    private String comments;
    private String name;
    private String id;
}
