package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.UserData;

import java.util.List;
import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
    public List<UserData> findAllByUserId(Long userId);
    public Optional<UserData> findDataByUserId(Long userId);
}
