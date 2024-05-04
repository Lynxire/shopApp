package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.UserData;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
    public Optional<UserData> findDataByUserId(Long userId);
}
