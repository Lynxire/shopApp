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
import terabu.entity.UserData;
import terabu.entity.status.Role;
import terabu.exception.user.UserAlreadyExistException;
import terabu.mapper.UserMapper;
import terabu.repository.UserDataRepository;
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
    private final UserDataRepository userDataRepository;

    @Transactional
    public UserResponse registerNewUserAccount(UserRequest accountDto){
        User user = userMapper.toEntity(accountDto);
        if(userRepositorySpringData.findByLogin(accountDto.getLogin()).isPresent() || userRepositorySpringData.findByEmail(accountDto.getEmail()).isPresent()){
            throw new UserAlreadyExistException("Пользователь с такой почтой или логином уже существует");
        }
        user.setDateRegistration(LocalDate.now());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        if(userRepositorySpringData.findAll().isEmpty()){
            user.setRole(Role.Admin);
        }
        user.setRole(Role.Client);

        userDataRepository.findByUserId(user.getId()).orElseGet(()->{
            UserData newData = new UserData();
            newData.setUser(user);
            userDataRepository.save(newData);
            return newData;
        });

        userRepositorySpringData.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepositorySpringData.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
