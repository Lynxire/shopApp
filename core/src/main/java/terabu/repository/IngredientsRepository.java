package terabu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import terabu.entity.Ingredients;

public interface IngredientsRepository extends JpaRepository<Ingredients, Long> {
}
