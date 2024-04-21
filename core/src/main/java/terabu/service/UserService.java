package terabu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import terabu.entity.Admin;
import terabu.entity.Client;
import terabu.entity.User;
import terabu.entity.status.Role;
import terabu.repository.AdminRepository;
import terabu.repository.ClientRepository;
import terabu.repository.UserRepositorySpringData;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepositorySpringData userRepositorySpringData;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;

    public void registerUser(User user) {
        if (userRepositorySpringData.findByEmail(user.getEmail()).isPresent() || userRepositorySpringData.findByLogin(user.getLogin()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        if (userRepositorySpringData.findAll().isEmpty()) {
            Admin admin = (Admin) user;
            admin.setRole(Role.Admin);
            admin.setLog("Admin register");
            adminRepository.save(admin);

        } else {
            Client client = (Client) user;
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


}
