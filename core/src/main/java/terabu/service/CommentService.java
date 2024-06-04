package terabu.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import terabu.dto.comment.CommentRequest;
import terabu.dto.comment.CommentResponse;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final RestTemplate restTemplate;

    public CommentResponse addComment(@RequestBody @Valid CommentRequest commentRequest) {
        return restTemplate.postForEntity("http://localhost:8082/comments/add",  commentRequest, CommentResponse.class).getBody();
    }


    public String deleteComment(@RequestParam @Min(1) @NotNull Long commentId) {
        return restTemplate.postForEntity("http://localhost:8082/comments/delete?commentId=" + commentId, null,  String.class).getBody();

    }

    public List<CommentResponse> getMyComments(@RequestParam @Min(1) @NotNull Long userId) {
        CommentResponse[] responses = restTemplate.getForEntity("http://localhost:8082/comments/myComments?userId=" + userId, CommentResponse[].class).getBody();
        return Arrays.asList(responses);
    }


    public List<CommentResponse> getAllComments() {
        CommentResponse[] responses = restTemplate.getForEntity("http://localhost:8082/comments/allComments", CommentResponse[].class).getBody();
        return Arrays.asList(responses);
    }
}
