package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.dto.comment.CommentRequest;
import terabu.dto.comment.CommentResponse;
import terabu.entity.Comments;
import terabu.entity.User;
import terabu.mapper.CommentMapper;
import terabu.repository.CommentsRepository;
import terabu.repository.UserRepositorySpringData;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;
    private final UserRepositorySpringData userRepository;

    public CommentResponse addComment(CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.getUserId()).get();
        Comments comments = commentMapper.toEntity(commentRequest);
        comments.setUser(user);
        commentsRepository.save(comments);
        return commentMapper.toResponse(comments);
    }

    public void deleteComment(CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.getUserId()).get();
        Comments comments = commentMapper.toEntity(commentRequest);
        comments.setUser(user);
        commentsRepository.delete(comments);
    }
}
