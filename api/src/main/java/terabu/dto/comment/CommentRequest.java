package terabu.dto.comment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

@Data
public class CommentRequest {
    @NotBlank(message = "пустое поле комментария")
    @Length(min = 1)
    private String comments;
    @NotNull
    @Min(1)
    private Long userId;
}
