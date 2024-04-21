package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import terabu.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
