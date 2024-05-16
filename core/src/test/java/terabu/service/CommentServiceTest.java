package terabu.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import terabu.dto.comment.CommentRequest;
import terabu.entity.Comments;
import terabu.entity.User;
import terabu.repository.CommentsRepository;
import terabu.repository.UserDataRepository;
import terabu.repository.UserRepositorySpringData;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentsRepository commentsRepository;
    @Mock
    private UserRepositorySpringData userRepositorySpringData;

    @InjectMocks
    private CommentService commentService;

    void addComments_ReturnToResponse(){
        when(userRepositorySpringData.findById(any())).thenReturn(Optional.of(new User()));
    }

}
