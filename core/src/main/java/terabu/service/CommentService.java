package terabu.service;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${comments.url}")
    private String url;
    private String urlAdd;
    private String urlDeleteById;
    private String urlByUserId;
    private String urlAll;

    @PostConstruct
    public void init() {
        urlAdd = url + "/add";
        urlDeleteById = url + "/delete?commentId=";
        urlByUserId = url + "/myComments?userId=";
        urlAll = url + "/allComments";
    }

    public CommentResponse addComment(@RequestBody @Valid CommentRequest commentRequest) {
        return restTemplate.postForEntity(urlAdd, commentRequest, CommentResponse.class).getBody();
    }


    public String deleteComment(@RequestParam @Min(1) @NotNull Long commentId) {
        return restTemplate.postForEntity(urlDeleteById + commentId, null, String.class).getBody();

    }

    public List<CommentResponse> getMyComments(@RequestParam @Min(1) @NotNull Long userId) {
        CommentResponse[] responses = restTemplate.getForEntity(urlByUserId + userId, CommentResponse[].class).getBody();
        return Arrays.asList(responses);
    }


    public List<CommentResponse> getAllComments() {
        CommentResponse[] responses = restTemplate.getForEntity(urlAll, CommentResponse[].class).getBody();
        return Arrays.asList(responses);
    }
}
