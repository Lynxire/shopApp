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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        List<Comments> list = List.of(comments());
        Mockito.when(commentsRepository.findAll()).thenReturn(list);
        when(commentMapper.toResponse(any())).thenReturn(commentResponse());
        Mockito.when(userDataRepository.findByUserId(any())).thenReturn(Optional.of(userData()));
        List<CommentResponse> result = commentService.getAllComments();

 
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(commentResponse(), result.get(0));
        verify(commentsRepository, times(1)).findAll();
        verify(commentMapper, times(1)).toResponse(comments());
        verify(userDataRepository, times(1)).findByUserId(1L);

    }

    private UserData userData(){
        UserData userData = new UserData();
        userData.setName("John Doe");
        userData.setUser(user());
        return userData;
    }

    private User user(){
        User user = new User();
        user.setId(1L);
        return user;
    }

    private CommentResponse commentResponse(){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setName("John Doe");
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
        comments.setId(1L);
        comments.setUser(user());
        comments.setComments("Test");
        return comments;
    }

}
