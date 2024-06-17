package terabu.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import terabu.dto.data.UserDataDTO;
import terabu.dto.users.UserDTO;

@RequiredArgsConstructor
@Service
public class UserDataService {
    private final RestTemplate restTemplate;

    @Value("${user_data.url}")
    private String url;
    private String urlByUserId;
    private String urlSave;

    @PostConstruct
    public void init() {
        urlByUserId = url + "/findByUserId?userId=";
        urlSave = url + "/save";
    }



    public UserDataDTO findDataByUserId(@RequestParam Long userId) {
        UserDataDTO body = restTemplate.getForEntity(urlByUserId + userId, UserDataDTO.class).getBody();
        return body;
    }

    public UserDTO save(@RequestBody UserDataDTO userDataDTO) {
        return restTemplate.postForEntity(urlSave, userDataDTO, UserDTO.class).getBody();
    }
}
