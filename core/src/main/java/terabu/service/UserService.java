package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.dto.users.UserRequest;
import terabu.dto.users.UserResponse;
import terabu.entity.Admin;
import terabu.entity.Client;
import terabu.entity.User;
import terabu.entity.status.Role;
import terabu.mapper.AdminMapper;
import terabu.mapper.ClientMapper;
import terabu.mapper.UserMapper;
import terabu.repository.AdminRepository;
import terabu.repository.ClientRepository;
import terabu.repository.UserRepositorySpringData;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositorySpringData userRepositorySpringData;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final UserMapper userMapper;
    private final ClientMapper clientMapper;
    private final AdminMapper adminMapper;

    @Transactional
    public UserResponse registerUser(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        if (userRepositorySpringData.findByEmail(user.getEmail()).isPresent() || userRepositorySpringData.findByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        if (userRepositorySpringData.findAll().isEmpty()) {
            Admin admin = adminMapper.toEntity(userRequest);
            admin.setRole(Role.Admin);
            admin.setLog("Admin register");
            adminRepository.save(admin);
            System.out.println("Admin registered successfully");
            return adminMapper.toResponse(admin);

        }

        Client client = clientMapper.toEntity(userRequest);
        client.setRole(Role.Client);
        client.setDateRegistration(LocalDate.now());
        clientRepository.save(client);
        System.out.println("User registered successfully");
        return clientMapper.toResponse(client);
    }

    public void authenticate(String email, String password) {
        if (userRepositorySpringData.findByEmail(email).isPresent() && password.equals(userRepositorySpringData.findByEmail(email).get().getPassword())) {
            System.out.println("Authenticated");
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public List<User> getAllUsers() {
        return userRepositorySpringData.findAll();
    }


}
