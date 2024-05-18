package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.users.UserRequest;
import terabu.dto.users.UserResponse;
import terabu.entity.User;
import terabu.entity.status.Role;
import terabu.mapper.UserMapper;
import terabu.repository.UserRepositorySpringData;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepositorySpringData userRepositorySpringData;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserResponse registerNewUserAccount(UserRequest accountDto){
        User user = new User();
        user.setDateRegistration(LocalDate.now());
        user.setLogin(accountDto.getLogin());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        if(userRepositorySpringData.findAll().isEmpty()){
            user.setRole(Role.Admin);
        }
        user.setRole(Role.Client);
        userRepositorySpringData.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositorySpringData.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
