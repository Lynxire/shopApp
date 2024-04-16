package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.User;
import terabu.entity.status.Role;
import terabu.repository.UserRepositorySpringData;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositorySpringData userRepositorySpringData;

    public void registerUser(User user) {
        if (userRepositorySpringData.findByEmail(user.getEmail()).isPresent() || userRepositorySpringData.findByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        if (userRepositorySpringData.findAll().isEmpty()) {
            user.setRole(Role.Admin);
        } else {
            user.setRole(Role.Client);
        }
        userRepositorySpringData.save(user);
        System.out.println("User registered successfully");
    }

    public void authenticate(String email, String password) {
        if (userRepositorySpringData.findByEmail(email).isPresent() && password.equals(userRepositorySpringData.findByEmail(email).get().getPassword())) {
            System.out.println("Authenticated");
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }


}
