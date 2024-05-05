package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Comments;

import java.util.List;
import java.util.Optional;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {
    public List<Comments> findAllByUserId(Long userId);
}
