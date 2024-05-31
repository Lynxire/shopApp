package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import terabu.entity.Comments;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    public List<Comments> findAllByUserId(Long userId);

}
