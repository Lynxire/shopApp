package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.User;

import java.util.Optional;


public interface UserRepositorySpringData extends JpaRepository<User, Long> {
    public Optional<User> findByLogin(String login);
    public Optional<User> findByEmail(String email);


}
