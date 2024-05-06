package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import terabu.dto.comment.CommentRequest;
import terabu.dto.comment.CommentResponse;
import terabu.service.CommentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
@Tag(name = "Контроллер для комментариев")
public class CommentsController {
    private final CommentService commentService;

    @Operation(summary = "Добавить комментариев")
    @PostMapping("/add")
    public CommentResponse addComment(@RequestBody CommentRequest commentRequest) {
        return commentService.addComment(commentRequest);
    }

    @Operation(summary = "Удалить комментарий")
    @PostMapping("/delete")
    public void deleteComment(@RequestParam Long commentId) {
        commentService.deleteComment(commentId);
    }

    @Operation(summary = "Комментарии пользователя", description = "Данный метод отвечает за просмотр своих комментариев пользователю")
    @GetMapping("/myComments")
    public List<CommentResponse> getMyComments(@RequestParam Long userId) {
        return commentService.getCommentByUser(userId);
    }
    @Operation(summary = "Все комментарии")
    @GetMapping("/allComments")
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComments();
    }

}
