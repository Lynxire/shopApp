package terabu.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import terabu.dto.users.UserDTO;


@Service
@RequiredArgsConstructor
public class UserService {
    private final RestTemplate restTemplate;

    @Value("${user.url}")
    private String url;
    private String urlByLogin;
    private String urlByEmail;
    private String urlById;

    @PostConstruct
    public void init() {
        urlByLogin = url + "/findByLogin?login=";
        urlByEmail = url + "/findByUser?email=";
        urlById = url + "/findById?id=";
    }


    public UserDTO findUserByLogin(@RequestParam String login){
        return restTemplate.getForEntity(urlByLogin + login, UserDTO.class).getBody();
    }

    public UserDTO findUserByEmail(@RequestParam String email){
        return restTemplate.getForEntity(urlByEmail + email, UserDTO.class).getBody();
    }

    public UserDTO findUserById(@RequestParam Long id){
        return restTemplate.getForEntity(urlById + id, UserDTO.class).getBody();
    }

}
