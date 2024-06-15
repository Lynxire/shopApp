package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import terabu.dto.users.UserDTO;


@Service
@RequiredArgsConstructor
public class UserService {
    private final RestTemplate restTemplate;
    final String URL = "http://localhost:8081/users";
    public UserDTO findUserByLogin(@RequestParam String login){
        return restTemplate.getForEntity("http://localhost:8081/user/findByLogin?login=" + login, UserDTO.class).getBody();
    }

    public UserDTO findUserByEmail(@RequestParam String email){
        return restTemplate.getForEntity("http://localhost:8081/user/findByUser?email=" + email, UserDTO.class).getBody();
    }

    public UserDTO findUserById(@RequestParam Long id){
        return restTemplate.getForEntity("http://localhost:8081/user/findById?id=" + id, UserDTO.class).getBody();
    }

}
