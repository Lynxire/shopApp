package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import terabu.entity.Admin;


public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
