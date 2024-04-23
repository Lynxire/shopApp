package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import terabu.entity.Admin;
import terabu.entity.Client;
import terabu.entity.User;
import terabu.entity.status.Role;
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

    @Transactional
    public void registerUser(User user) {
        if (userRepositorySpringData.findByEmail(user.getEmail()).isPresent() || userRepositorySpringData.findByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        if (userRepositorySpringData.findAll().isEmpty()) {
            Admin admin = new Admin();
            admin.setLogin(user.getLogin());
            admin.setPassword(user.getPassword());
            admin.setEmail(user.getEmail());
            admin.setRole(Role.Admin);
            admin.setLog("Admin register");
            adminRepository.save(admin);

        } else {
            Client client = new Client();
            client.setLogin(user.getLogin());
            client.setPassword(user.getPassword());
            client.setEmail(user.getEmail());
            client.setRole(Role.Client);
            client.setDateRegistration(LocalDate.now());
            clientRepository.save(client);
        }

        System.out.println("User registered successfully");
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
