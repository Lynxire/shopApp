package terabu.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import terabu.service.CommentsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
@Tag(name = "Контроллер для комментариев")
public class CommentsController {
    private final CommentsService commentsService;


}
