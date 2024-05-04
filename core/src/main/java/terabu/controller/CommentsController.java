package terabu.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import terabu.dto.comment.CommentRequest;
import terabu.dto.comment.CommentResponse;
import terabu.service.CommentService;

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
    public void deleteComment(@RequestBody CommentRequest commentRequest) {
        commentService.deleteComment(commentRequest);
    }

}
