package terabu.service;

import lombok.RequiredArgsConstructor;
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

    public UserDataDTO findDataByUserId(@RequestParam Long userId){
        UserDataDTO body = restTemplate.getForEntity("http://localhost:8081/data/findByUserId?userId=" + userId, UserDataDTO.class).getBody();
        return body;
    }

    public UserDTO save(@RequestBody UserDataDTO userDataDTO){
        return restTemplate.postForEntity("http://localhost:8081/data/save", userDataDTO, UserDTO.class).getBody();
    }
}
