package terabu.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import terabu.dto.comment.CommentRequest;
import terabu.dto.comment.CommentResponse;
import terabu.entity.Comments;
import terabu.entity.User;
import terabu.entity.UserData;
import terabu.mapper.CommentMapper;
import terabu.repository.CommentsRepository;
import terabu.repository.UserDataRepository;
import terabu.repository.UserRepositorySpringData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @Mock
    private CommentsRepository commentsRepository;
    @Mock
    private UserRepositorySpringData userRepositorySpringData;

    @Mock
    private UserDataRepository userDataRepository;

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentService commentService;


    @Test
    void testGetAllComments(){
        Mockito.when(commentsRepository.findAll()).thenReturn(new ArrayList<>());
        UserData mockUserData = new UserData();
        mockUserData.setName("John Doe");
        Mockito.when(userDataRepository.findByUserId(any())).thenReturn(Optional.of(mockUserData));
        List<CommentResponse> result = commentService.getAllComments();


    }

    private CommentResponse commentResponse(){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setComments("Test");
        return commentResponse;
    }
    private CommentRequest commentRequest(){
        CommentRequest commentRequest = new CommentRequest();
        commentRequest.setComments("Test");
        commentRequest.setUserId(1L);
        return commentRequest;
    }

    private Comments comments(){
        Comments comments = new Comments();
        comments.setComments("Test");
        return comments;
    }

}
