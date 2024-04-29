package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Comments;

public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}
