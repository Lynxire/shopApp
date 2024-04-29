package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Comments;
import terabu.entity.User;
import terabu.repository.CommentsRepository;
import terabu.repository.UserRepositorySpringData;

@RequiredArgsConstructor
@Service
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final UserRepositorySpringData userRepository;

    public void addComment(Long userId, String comment) {
        User user = userRepository.findById(userId).get();
        Comments comments = new Comments();
        comments.setComments(comment);
        comments.setUser(user);
        commentsRepository.save(comments);

    }
}
