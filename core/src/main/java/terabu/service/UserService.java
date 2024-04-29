package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.users.UserRequest;
import terabu.dto.users.UserResponse;
import terabu.entity.User;
import terabu.entity.status.Role;
import terabu.mapper.UserMapper;
import terabu.repository.UserRepositorySpringData;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepositorySpringData userRepositorySpringData;
    private final UserMapper userMapper;



    public UserResponse registerUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        if (userRepositorySpringData.findByEmail(user.getEmail()).isPresent() || userRepositorySpringData.findByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        if (userRepositorySpringData.findAll().isEmpty()) {
            user.setRole(Role.Admin);
            user.setDateRegistration(LocalDate.now());
            userRepositorySpringData.save(user);
            System.out.println("Admin registered successfully");
            return userMapper.toResponse(user);

        }
        user.setDateRegistration(LocalDate.now());
        user.setRole(Role.Client);
        userRepositorySpringData.save(user);
        System.out.println("User registered successfully");
        return userMapper.toResponse(user);
    }

    public UserResponse authenticate(UserRequest userRequest) {
        User userMapperEntity = userMapper.toEntity(userRequest);
        if (userRepositorySpringData.findByEmail(userMapperEntity.getEmail()).isPresent() && userMapperEntity.getPassword().equals(userRepositorySpringData.findByEmail(userMapperEntity.getEmail()).get().getPassword())) {
            System.out.println("Authenticated");
            User user = userRepositorySpringData.findByEmail(userMapperEntity.getEmail()).get();
            return userMapper.toResponse(user);
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public List<User> getAllUsers() {
        return userRepositorySpringData.findAll();
    }


}
