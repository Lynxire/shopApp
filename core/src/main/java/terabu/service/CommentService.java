package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
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
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentsRepository commentsRepository;
    private final CommentMapper commentMapper;
    private final UserRepositorySpringData userRepository;
    private final UserDataRepository userDataRepository;
    public CommentResponse addComment(CommentRequest commentRequest) {
        User user = userRepository.findById(commentRequest.getUserId()).get();
        Comments comments = commentMapper.toEntity(commentRequest);
        comments.setUser(user);
        commentsRepository.save(comments);
        return commentMapper.toResponse(comments);
    }

    public void deleteComment(Long commentId) {
        commentsRepository.findById(commentId).ifPresent(commentsRepository::delete);

    }

    public List<CommentResponse> getCommentByUser(Long userId) {
        UserData data = userDataRepository.findDataByUserId(userId).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
        List<Comments> list = commentsRepository.findAllByUserId(userId);
        List<CommentResponse> responses = new ArrayList<>();
        list.forEach(comments -> {
            CommentResponse commentResponse = commentMapper.toResponse(comments);
            commentResponse.setName(data.getName());
            responses.add(commentResponse);
        });

        return responses;
    }

//    Пагинацичя
    public List<CommentResponse> getAllComments(){
        List<Comments> commentsList = commentsRepository.findAll();
////        List<CommentResponse> responses = new ArrayList<>();
//        commentsList.forEach(comments -> {
//            User user = comments.getUser();
//            UserData userData = userDataRepository.findByUserId(user.getId());
//            CommentResponse commentResponse = commentMapper.toResponse(comments);
//            commentResponse.setName(userData.getName());
//            responses.add(commentResponse);
//        });
//        return responses;

        return commentsList.stream().map(comments ->{
            CommentResponse commentResponse = commentMapper.toResponse(comments);
            UserData userData = userDataRepository.findByUserId(comments.getUser().getId());
            commentResponse.setName(userData.getName());
            return commentResponse;
        }).toList();



    }
}
