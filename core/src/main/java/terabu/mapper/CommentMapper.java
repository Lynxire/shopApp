package terabu.mapper;

import org.mapstruct.Mapper;
import terabu.dto.comment.CommentRequest;
import terabu.dto.comment.CommentResponse;
import terabu.entity.Comments;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    public Comments toEntity(CommentRequest commentRequest);
    public CommentResponse toResponse(Comments comments);
}
