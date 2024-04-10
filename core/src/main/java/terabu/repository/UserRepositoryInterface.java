package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.User;

import java.util.List;

public interface UserRepositoryInterface{
    public void add (User user);
    public void deleteById(Long id);
    public List<User> viewAllUsers();
}
