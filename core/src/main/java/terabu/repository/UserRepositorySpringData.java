package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import terabu.entity.User;

import java.util.List;

@Repository
public interface UserRepositorySpringData extends JpaRepository<User, Long> {
    public List<User> findAll();

    public User findUserById(Long id);

    @Modifying
    public void deleteById(Long id);

    @Modifying
    public User save(User user);


}
