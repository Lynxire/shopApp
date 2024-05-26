package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Добавить комментариев")
    @PostMapping("/add")
    public CommentResponse addComment(@RequestBody @Valid CommentRequest commentRequest) {
        return commentService.addComment(commentRequest);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Удалить комментарий")
    @PostMapping("/delete")
    public void deleteComment(@RequestParam @Min(1) @NotNull Long commentId) {
        commentService.deleteComment(commentId);
    }
    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Комментарии пользователя", description = "Данный метод отвечает за просмотр своих комментариев пользователю")
    @GetMapping("/myComments")
    public List<CommentResponse> getMyComments(@RequestParam @Min(1) @NotNull Long userId) {
        return commentService.getCommentByUser(userId);
    }

    @Operation(summary = "Все комментарии")
    @GetMapping("/allComments")
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComments();
    }

}
