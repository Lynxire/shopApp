package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Comments;
import terabu.entity.User;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    public List<Comments> findAllByUserId(Long userId);
}
