package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import terabu.entity.Comments;
import terabu.entity.User;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    public List<Comments> findAllByUserId(Long userId);

    @Query(value = "select distinct c.user.id from Comments c where c.id in :commentsIds")
    List<Long> findAllByCommentsIds(@Param("commentsIds") List<Long>commentsIds);
}
